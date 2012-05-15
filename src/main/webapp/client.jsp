<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getPathInfo();
        
        String[] chunks = path != null ? path.split("/") : null;
        if (chunks == null || chunks.length <= 1) {
            pageContext.forward("noRoom.html");
            return;
        }
        String room = chunks[1];
        String member = null;
        if (chunks.length > 2)
            member = chunks[2];

    %>
    <link rel="stylesheet" type="text/css" href="/resources/style.css"/>
    <!--[if !IE]>-->
    <link type="text/css" rel="stylesheet" media="only screen and (max-device-width: 480px) and (min-device-width: 768px) and (max-device-width: 1024px)" 
          href="/resources/style-mobile.css" />
    <!--<![endif]-->
    
    <script type="text/javascript" src="/resources/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/resources/jquery.dateFormat-1.0.js"></script>
    <script type="text/javascript">
        var room = "<%= room%>";
        var member = '<%= member != null ? member : "" %>';
        var timer;
        $(document).ready(function () {
            if (member.length == 0)
                register();
            else
                init();
        });
        
        function init() {
            document.title = '#' + room + ' @ SpeeChat';
            callServer();
            $("#author").text(member);
            $("#post").submit(function () {
                post(room, member);
                return false;
            });
        }

        function register() {
            $.ajax({url:"/rest/" + room }).done(function(data) {
                document.location = document.location + "/" + data;
            });
        }
        

        function updateContainer(data) {
            var response = eval(data);
            $(response.messages).each(function (index, msg) {
                var sender = $("<div/>").addClass("author").text(msg.author + ":");
                var msgLabel = $("<div/>").addClass("content").text(msg.message);
                var date = $.format.date(new Date(msg.date), "MMM, dd HH:mm");
                var dateLabel = $("<div/>").addClass("date").text(date);

                var msgDiv = $("<div/>").addClass("message").append(sender).append(msgLabel).append(dateLabel);
                $("#container").append(msgDiv);
            });
            timer = setTimeout(poll, 2000);
        }
        function poll() {
            callServer("/new");
        }
        
        function callServer(suffix) {
            if (!suffix) suffix = '';
            $.ajax({url:"/rest/" + room + "/" + member + suffix}).done(updateContainer);
        }

        function post(room, member) {
            var msgInput = $("#message");
            var msg = msgInput.val();
            if (msg.trim().length == 0)
                return;
            msgInput.val('');
            msgInput.attr('disabled', 'disabled');
            $("#submit").attr('disabled', 'disabled');

            $.ajax({
                       type:"POST",
                       url:"/rest/" + room + "/" + member,
                       data:{message:msg}
                        
                   }).always(postPost).fail(function(){failed("post")})
        }
        function postPost() {
            $("#message").removeAttr('disabled');
            $("#submit").removeAttr('disabled');
        }
        
        function cancelPoll() {
            if (timer) {
                clearTimeout(timer);
            }
        }
        
        function failed(msg) {
            alert(msg + " failed. Try again later");
        }
    </script>
</head>
<body>
<div id="container">
</div>
<div>
    <form id="post" action="" method="post">
        <label for="message" id="author" class="author-label"></label>:
        <input name="message" id="message" class="message-input" maxlength="140"/>
        <input type="submit" id="submit" value="Post" class="post-button"/>
        <button value="Stop auto refresh" onclick="cancelPoll()">Don't auto-refresh</button>
    </form>
</div>
</body>
</html>
<%@ page import="servlet.data.UserInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chatroom</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybBud7M4+JytO1bmFjFqeBxB5U5yPtU9FkoCM6+Rft6jn3D6a" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <%@ include file="homebtn.html" %>

</head>

<%
    UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
    int s_id = Integer.parseInt(request.getParameter("s_id"));
    int t_id = Integer.parseInt(request.getParameter("t_id"));
%>

<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    聊天室
                </div>
                <div class="card-body" id="messages" style="height: 300px; overflow-y: scroll;">
                    <!-- Chat messages will be displayed here -->
                </div>
                <div class="card-footer">
                    <form id="message-form">
                        <div class="input-group">
                            <input type="text" class="form-control" id="message-input" placeholder="输入消息" required/>
                            <button type="submit" class="btn btn-primary">发送</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    let lastMessage = "";

    fetchMessages();

    // Fetch messages from the server
    function fetchMessages() {
        $.ajax({
            url: "fetchMessage",
            type: "GET",
            dataType: "json",
            success: function (data) {
                let messagesHTML = "";
                data.forEach(function (messageInfo) {
                    if (messageInfo.send == <%=userInfo.getRoleReal()%>){
                        messagesHTML += "<div><strong>"+messageInfo.t_name+": </strong>"+messageInfo.message+"</div>";
                    }
                    else {
                        messagesHTML += "<div><strong>"+messageInfo.s_name+": </strong>"+messageInfo.message+"</div>";
                    }

                });
                if (messagesHTML !== lastMessage) {

                    $("#messages").html(messagesHTML);
                    if (lastMessage !== ""){
                        $("#messages").scrollTop($("#messages")[0].scrollHeight);
                    }

                    lastMessage = messagesHTML;
                }

            }
        });

    }

    // Send a message to the server
    function sendMessage(message) {
        $.ajax({
            url: "sendMessage?s_id=<%=s_id%>&t_id=<%=t_id%>",
            type: "POST",
            data: {
                message: message
            },
            success: function () {
                fetchMessages();
            }
        });
    }

    $("#message-form").on("submit", function (event) {
        event.preventDefault();
        const messageInput = $("#message-input");
        const message = messageInput.val().trim();

        if (message) {
            sendMessage(message);
            messageInput.val("");
        }
    });

    // Poll the server for new messages every 5 seconds
    setInterval(fetchMessages, 800);
</script>
</body>
</html>

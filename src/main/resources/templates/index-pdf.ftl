<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <link href="css/main.css" rel="stylesheet"/>
    <style>
    #users {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#users td, #users th {
    border: 1px solid #ddd;
    padding: 8px;
}

#users tr:nth-child(even){background-color: #f2f2f2;}

#users tr:hover {background-color: #ddd;}

#users th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
}
    </style>
</head>
<body>
  <div style="margin: 10px;">
    <h2 class="hello-title">User List</h2>
    </div>
<div id="content" style="width:500px">
  
  <table class="datatable" id="users">
    <tr>
      <th>User</th>
      <th>Age</th>
    </tr>
  <#list userList as user>
      <tr>
        <td>${user.name}</td>
        <td>${user.age}</td>
      </tr>
    </#list>
  </table>
</div>
    <script src="js/main.js"></script>
</body>
</html>
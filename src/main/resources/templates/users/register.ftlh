<!doctype html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registrierung</title>
</head>
<#import "../layout.ftlh" as layout>
<@layout.layout>
    <body>
    <div class="register">
        <h2>Registrierung</h2>
        <form action="/register" method="post">
            <input type="text" name="email" placeholder="Email-Adresse">
            <input type="text" name="name" placeholder="Name">
            <input type="password" name="password" placeholder="Passwort">
            <input type="password" name="password" placeholder="Passwort wiederholen">
            <button type="submit">Registrieren</button>
        </form>
    </div>
    </body>
</@layout.layout>
</html>
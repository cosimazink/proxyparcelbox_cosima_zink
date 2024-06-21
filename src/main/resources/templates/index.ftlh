<#import "layout.ftlh" as base>
<@base.layout>
    <h2>Abgelegtes Paket anlegen</h2>
    <form action="/chats" method="post">
        <input type="text" name="trackingNumber" placeholder="Bitte Sendungsnummer eintippen...">
        <input type="text" name="email" placeholder="E-Mail des Nachbarn (optional)">
        <button type="submit">Abschicken</button>
    </form>
</@base.layout>
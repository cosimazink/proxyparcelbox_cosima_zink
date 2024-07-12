<#macro layout>
    <!DOCTYPE html>
    <html lang="de">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ProxyParcelBox - Cosima Zink</title>
        <link rel="stylesheet" href="/css/reset.css">
        <link rel="stylesheet" href="/css/base.css">
        <link rel="stylesheet" href="/css/variables.css">
        <link rel="stylesheet" href="/css/components.css">
    </head>

    <body>
    <header>
        <div class="navigation">
            <ul>
                <li><img src="/images/LogoProxyParcelBox.png" alt="Send" style="height: 50px;"></li>
                <li><a href="/support">Support</a></li>
                <li><a href="/überUns">Über uns</a></li>
            </ul>
            <ul>
                <li><button><a href="/emailCheck">Login</a></button></li>
                <li><button><a href="/register">Registrierung</a></button></li>
            </ul>
        </div>
    </header>

    <main>
        <#nested>
    </main>

    <footer>
        <p>Cosima Hiromi Zink</p>
    </footer>

    </body>
    <script src="/javascript/sendMessage.js"></script>
    </html>
</#macro>
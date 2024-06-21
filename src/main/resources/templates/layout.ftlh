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
                <li><a href="/">Chat starten</a></li>
                <li><a href="/support">Support</a></li>
                <li><a href="/überUns">Über uns</a></li>
            </ul>
            <ul>
                <li><button>Login</button></li>
                <li><button>Registrierung</button></li>
            </ul>
        </div>
    </header>

    <main>
        <#nested>
    </main>

    <footer>
        <p>Cosima Zink</p>
    </footer>

    </body>

    </html>
</#macro>
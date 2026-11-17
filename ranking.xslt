<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <title>Ranking - El Despertar del Héroe</title>
                <link rel="stylesheet"  href="estilos.css"/>
            </head>
            <body>
                <div class="contenedor">
                    <h1>EL DESPERTAR DEL HÉROE</h1>
                    <h2>Top 10 Jugadores</h2>

                    <table>
                        <thead>
                            <tr>
                                <th>Posición</th>
                                <th>Héroe</th>
                                <th>Puntuación</th>
                            </tr>
                        </thead>
                        <tbody>
                            <xsl:for-each select="ranking/jugador">
                                <tr>
                                    <td class="posicion"><xsl:value-of select="position()"/></td>
                                    <td class="nombre"><xsl:value-of select="nombre"/></td>
                                    <td class="puntuacion"><xsl:value-of select="puntuacion"/> pts</td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method='html' version='1.1' encoding='UTF-8' indent='yes'/>
<xsl:template match="/">
  <html>
  <head>
      <title>CD Catalog</title>
      <style type="text/css">
        table {
            font-family: verdana;
        }

          tr {
              height: 30px;
          }

          td.col1 {
              width: 100px;
          }

          td.col2 {
              width: 400px;
          }

          td.title {
              background: #efe7d9 ;
              border-bottom: 1px solid #336699;
              font: 16 verdana;
              padding: 0 0 0 15px;
          }

          .red {
              color: #ff0000;
          }

          .green {
              color: #249821;
          }

          td.greenRow
          {
              background: #d6efd6;
              border-bottom: 1px solid #437841;

          }
      </style>
   </head>
  
  <body>
  <h2> from XML and XSLT StyleSheet Using iText, Flying Saucer and Java XSLT Transformer</h2>
    <table border="1">
      <tr>
        <th align="left">Title</th>
        <th align="left">Price</th>
      </tr>
      <xsl:for-each select="ConvertXMLtoPDF/TestData">
      <tr>
        <td class ='title'><xsl:value-of select="Title"/></td>
        <td><xsl:value-of select="Price"/></td>
      </tr>
      </xsl:for-each>
    </table>    
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

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
					font: 16
					verdana;
					padding: 0 0 0 15px;
					}

					.red {
					color: #ff0000;
					}

					.green {
					color:
					#249821;
					}

					td.greenRow
					{
					background: #d6efd6;
					border-bottom: 1px solid
					#437841;

					}
					
					div
					{
					display: inline
					}
				</style>
			</head>

			<body>
				<div>


					<div>trackId:</div>
					<div class='red'>
						<xsl:value-of select="raceData/race/trackId" />
					</div>

					<div>Coutry:</div>
					<div class='red'>
						<xsl:value-of select="raceData/race/country" />
					</div>

					<div>raceDate:</div>
					<div class='red'>
						<xsl:value-of select="raceData/race/raceDate" />
					</div>
					<table>
						<tr>
							<td class='title col1'>ReG no</td>
							<td class='title col1'>Odds</td>
							<td class='title col1'>color</td>
							<td class='title col1'>damName</td>
							<td class='title col1'>sireName</td>
							<td class='title col1'>ownerType</td>
						</tr>


						<xsl:for-each select="/raceData/horses">

							<tr>
								<td class='col1'><xsl:value-of select="registrationNumber" /></td>
								<td class='col1'><xsl:value-of select="morningLineOdds" /></td>
								<td class='col1'><xsl:value-of select="color" /></td>
								<td class='col1'><xsl:value-of select="damName" /></td>
								<td class='col1'><xsl:value-of select="sireName" /></td>
								<td class='col1'><xsl:value-of select="ownerName" /></td>
							</tr>

						</xsl:for-each>
					</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
	#carousel{
		max-height: 500px;
		max-width: 1140px
	}
	.carousel-img {
		max-height: 500px;
		margin: 0 auto
	}
	.thumbnail a img {
		max-height: 250px;
	}

	</style>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <!--carousel-->
       <div id="carousel" class="carousel slide container" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<a href="${pageContext.request.contextPath}/viewItem"><img src="${pageContext.request.contextPath}/resources/img/1.JPG" alt="..." class="carousel-img">
				<div class="carousel-caption">
					<!-- <h3>2038.46 บาท</h3> -->
					<h4>BRANDIT M-65 Classic Herren Jacke schwarz Feldjacke BW Kapuze Fieldjacket M65</h4>
				</div>
				</a>
			</div>
			<div class="item">
				<img src="${pageContext.request.contextPath}/resources/img/2.JPG" alt="..." class="carousel-img">
				<div class="carousel-caption">
					<!-- <h3>1303.88 บาท</h3> -->
					<h4>Levis 501 Mens Jeans Stonewashed W30 W32 W34 W36 W38 +</h4>
				</div>
			</div>
			<div class="item">
				<img src="${pageContext.request.contextPath}/resources/img/3.JPG" alt="..." class="carousel-img">
				<div class="carousel-caption">
					<!-- <h3>1693.84 บาท</h3> -->
					<h4>Black Rivet Fully Lined Faux-Leather Scuba Jacket w/ Knit Inset</h4>
				</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="right carousel-control" href="#carousel" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>

	<br>
<!--container-->
	<div class="container">
		<h3>สินค้าล่าสุด</h3>
		<hr>
		<div class="row">
			<div class="col-sm-4 col-md-3">
				<div class="thumbnail">
					<a href="${pageContext.request.contextPath}/viewItem"><img src="${pageContext.request.contextPath}/resources/img/1.JPG" alt="..."></a>
					<div class="caption">
						<h3>2038 บาท</h3><h4>ซื้อทันที</h4>
						<p>BRANDIT M-65 Classic Herren Jacke schwarz Feldjacke BW Kapuze Fieldjacket M65</p>
					</div>
				</div>
			</div>
			<div class="col-sm-4 col-md-3">
				<div class="thumbnail">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/img/2.JPG" alt="..."></a>
					<div class="caption">
						<h3>1303 บาท</h3><h4>ซื้อทันที</h4>
						<p>Levis 501 Mens Jeans Stonewashed W30 W32 W34 W36 W38 +</p>
					</div>
				</div>
			</div>
			<div class="col-sm-4 col-md-3">
				<div class="thumbnail">
					<a href="#"><img src="${pageContext.request.contextPath}/resources/img/3.JPG" alt="..."></a>
					<div class="caption">
						<h3>1693 บาท</h3><h4>ซื้อทันที</h4>
						<p>Black Rivet Fully Lined Faux-Leather Scuba Jacket w/ Knit Inset</p>
					</div>
				</div>
			</div>
			<div class="col-sm-4 col-md-3">
				<div class="thumbnail">
					<a href="${pageContext.request.contextPath}/viewItem-Bid"><img src="${pageContext.request.contextPath}/resources/img/4.JPG" alt="..."></a>
					<div class="caption">
						<h3>105 บาท</h3><h4>0 bids</h4>
						<p>FROM OUT OF THE WEST, COUNTRY , CASSETTE TAPE</p>
					</div>
				</div>
			</div>
		</div>
	</div>


    
    </tiles:putAttribute>
</tiles:insertDefinition>
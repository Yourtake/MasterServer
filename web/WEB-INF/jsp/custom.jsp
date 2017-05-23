<%-- 
    Document   : home
    Created on : Jul 19, 2016, 11:32:51 PM
    Author     : MumbaiZone
--%><%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
                                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                                    <meta name="viewport" content="width=device-width, initial-scale=1">
                                    <meta name="description" content="">
                                    <meta name="author" content="Souvik Das">
                                    <title>${page.header.text}</title>
                                    <link href="<c:url value="/resources/images/logo.png" />" rel="shortcut icon">
                                    
                                    
                                    <link href="https://fonts.googleapis.com/css?family=Lato:300" rel="stylesheet">
                                    
                                    <link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.4.css" />" rel="stylesheet">
            
            <link type="text/css" href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
             <link type="text/css" href="<c:url value="/resources/css/star-rating.min.css" />" rel="stylesheet">
             <link type="text/css" href="<c:url value="/resources/css/theme.min.css" />" rel="stylesheet">
            <link href="https://fonts.googleapis.com/css?family=Lato:300" rel="stylesheet">
            
                    <link type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
                     <script async type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
                     <link type="text/css" href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet">        
                     <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
                     <script type="text/javascript" src="<c:url value="/resources/js/bootbox.min.js" />"></script>
            <script src="<c:url value="/resources/js/star-rating.min.js"/>" type="text/javascript"></script>  
            <script src="<c:url value="/resources/js/theme.min.js"/>" type="text/javascript"></script>
                                    
                    </head>
	<body>
		<div class="site-wrapper">
                                                <div class="site-wrapper-inner">
                                                        <div class="container">
                                                                <div class="header clearfix">
                                                                        <center>
                                                                                <img id="logo" src="${page.header.image}" width="100" height="90" class="img-responsive" alt="Generic placeholder thumbnail">
                                                                                        <h3 >${page.header.text}</h3>
                                                                                        <br/>
                                                                                        <h5>${page.header.subText}</h5>
                                                                                         <hr/>
                                                                        </center>
                                                                </div>
                                                        </div>
                                                            
                                                        <div class="container box">                             
                                                                  <div class="row">
                                                                    <div class="col-xs-12">
                                                                        <a  href="${pageContext.request.contextPath}/${page.relativeUrl}/${page.header.url}"> <h3 style="text-align: left"><span class="glyphicon glyphicon-circle-arrow-left"></span>&nbsp;Back</h3></a>
                                                                    </div>
                                                                 </div>
                                                        </div>
                                                                    
                                                                    <c:if test="${not empty page.tabs}">
                                                                            <div class="container box">
                                                                                    <center>
                                                                                            <div class="row placeholders">

                                                                                            <c:forEach items="${page.tabs}" var="tab" varStatus="i">
                                                                                                    <div class="col-xs-6 panel">
                                                                                                            <div class=" placeholder">
                                                                                                                    <br/>
                                                                                                                    <a  href="${pageContext.request.contextPath}/${page.relativeUrl}/${tab.url}">
                                                                                                                            <img id="menu-box" src="${tab.imageUrl}" width="50" height="50" class="img-responsive" alt="Menu">
                                                                                                                                    <h4  class="text-muted">
                                                                                                                                            <b>${tab.text}</b>
                                                                                                                                    </h4>
                                                                                                                                    <span class="text-muted">  ${tab.subText}</span>
                                                                                                                    </a>
                                                                                                            </div>
                                                                                                </div>   
                                                                                               </c:forEach>
                                                                                            </div>
                                                                                    </center>
                                                                            </div>
                                                        
                                                                    </c:if>
                                                                     <c:if test="${not empty page.forms}">
                                                                            <div class="container">
                                                                                         <div class="row">      
                                                                                                                   <form id="feedback-form" action="${pageContext.request.contextPath}/${page.relativeUrl}/${page.submitUrl}" method="POST" class="form-signin-heading" >

                                                                                                                        <div class="form-group">
                                                                                                                        <div class="col-md-4" style="padding-right: 30px">
                                                                                                                            <c:forEach items="${page.forms}" var="form" varStatus="i">
                                                                                                                                 <div class="col-xs-10">
                                                                                                                                         <h4><span class="glyphicon glyphicon-bell"></span>&nbsp;${form.text}</h4>
                                                                                                                                         <c:if test="${form.type=='Text'}">
                                                                                                                                                <textarea name="name" class="form-control" required="true" rows="1"  placeholder="Your name"></textarea>
                                                                                                                                         </c:if>
                                                                                                                                         <c:if test="${form.type=='Rating'}">
                                                                                                                                                 <input id="service" name="service" required="true" type="text" class="rating rating-loading"  data-size="xs"title="">
                                                                                                                                         </c:if>
                                                                                                                                         </div>
                                                                                                                                    <div class="col-xs-12"><hr/></div>
                                                                                                                            </c:forEach>
                                                                                                                                   <div class="col-xs-10">
                                                                                                                                     <br/><center>
                                                                                                                                                  <button   type="submit"   class="btn btn-default btn-lg shift">Submit</button>
                                                                                                                                             </center>
                                                                                                                                   </div>
                                                                                                                                   
                                                                                                                                   
                                                                                                                        </div>   
                                                                                                                        </div>
                                                                                                                   </form>
                                                                                         </div>
                                                                            </div>
                                                                     </c:if>
                                                                                                  
                                                                                                  
                                          <br/>
                               <center>
                                   Powered by <img  src="${pageContext.request.contextPath}/resources/images/yourtake.png" width="50" height="25"/>
                               </center>
                                                </div>
                                        </div>
	</body>
</html>
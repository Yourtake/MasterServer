<%-- 
    Document   : adminlogin
    Created on : 22 Sep, 2014, 9:58:51 PM
    Author     : Welcome
--%>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <title>Home</title>
             <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
            
               <link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.4.css" />" rel="stylesheet">
            
             <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />" rel="stylesheet">
          <script async type="text/javascript" src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js" />"></script>
            
            <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />" rel="stylesheet">            
            
         
             <script  type="text/javascript" src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"/>"></script>
          
            <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
            
            
            <script async type="text/javascript" src="<c:url value="//cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.3.0/bootbox.min.js" />"></script>
  
            
           <link type="text/css" href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet">
                                    <script src="<c:url value="/resources/js/dashboard.js" />" type="text/javascript"></script>
                                    
                                    
            
    </head>
    <body>
        <tiles:insertAttribute name="navigation"/>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="row">
                  <div class="col-sm-5 well">
                     <label ><b><u>Search Organizations</u><b></label>
                                 <br/>
                <form action="${pageContext.request.contextPath}/admin/rules/orgnization/search" method="POST" class="form-signin-heading" >
                        <input type="text" class="form-control" placeholder="Url" name="url"  required="true"/>
                    <br/>
                      <button type="submit" class="btn btn-sm" style="background-color:#515151;color:#ffffff" >Search</button>
                        <br/>
                      <c:if test="${not empty rules}">
                                        <c:forEach items="${rules}" var="branch">
                                                   <a href="${pageContext.request.contextPath}/admin/rules?rule=${rule.id}&org=${orgId}" class="btn btn-sm" style="background-color:#0081c2;color:#ffffff" >${rule.id}</a>&nbsp;
                                        </c:forEach>
                                    <br/>
                       </c:if>
                </form>
                 </div>
                        
                        
                <c:if test="${not empty orgId}">
                         <div class="col-sm-5 well col-sm-offset-2">
                     <label ><b><u>Add/Edit Rules</u><b></label>
                                 <br/>
                <form action="${pageContext.request.contextPath}/admin/rules/build" method="POST" class="form-signin-heading" >
                    <input type="text" class="form-control" placeholder="Form Name" name="name" value="${rule.formInputName}" required="true"/>
                        <br/>
                        <input type="text" class="form-control" placeholder="Lower Cap" name="lower" value="${rule.lowerCap}" required="true"/>
                        <br/>
                        <input type="text" class="form-control" placeholder="Upper Cap" name="upper" value="${rule.upperCap}" required="true"/>
                        <br/>
                      <input type="text" class="form-control" placeholder="Email Ids" name="emails" value="${emails}" required="true"/>
                        <br/>
                        <input style="display:none" type="text" class="form-control" name="orgId" value="${orgId}" required="true"/>
                        <br/>
                      <button type="submit" class="btn btn-sm" style="background-color:#515151;color:#ffffff" >Add</button>
                </form>
                 </div>
                </c:if>
            </div>
                   
        </div>
    </div>
</div>
    </body>
</html>

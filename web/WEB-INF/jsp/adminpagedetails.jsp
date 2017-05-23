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
           
            <%--
           
           <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
           <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.js"></script>
           <link type="text/css" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.css">
            --%>
            
           
           
           <%--
            <script type="text/javascript" src="<c:url value="/resources/js/doc.js" />"></script> 
            <script type="text/javascript" src="<c:url value="/resources/js/respond.js" />"></script>
                 --%>
            
           <link type="text/css" href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet">
                                    <script src="<c:url value="/resources/js/dashboard.js" />" type="text/javascript"></script>
                                    
                                    
            
    </head>
    <body>
        <tiles:insertAttribute name="navigation"/>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
             <div class="row">
                 <div class="col-sm-5 well">
                     <label ><b><u>Search Pages</u><b></label>
                                 <br/>
                <form action="${pageContext.request.contextPath}/admin/pages/det/search" method="POST" class="form-signin-heading" >
                        <input type="text" class="form-control" placeholder="Name" name="name"  required="true"/>
                    <br/>
                      <button type="submit" class="btn btn-sm" style="background-color:#515151;color:#ffffff" >Search</button>
                      <br/>
                      <c:if test="${not empty formInputs}">
                                        <c:forEach items="${formInputs}" var="fi">
                                                   <a href="${pageContext.request.contextPath}/admin/fi?fi=${fi.id}&page=${pageId}" class="btn btn-sm" style="background-color:#0081c2;color:#ffffff" >${fi.name}</a>&nbsp;
                                        </c:forEach>
                                    <br/>
                                    <hr/>
                       </c:if>
                                    <br/>
                        <c:if test="${not empty tabs}">
                                        <c:forEach items="${tabs}" var="tab">
                                                   <a href="${pageContext.request.contextPath}/admin/tab?tab=${tab.id}&page=${pageId}" class="btn btn-sm" style="background-color:#0081c2;color:#ffffff" >${tab.name}</a>&nbsp;
                                        </c:forEach>
                                    <br/>
                       </c:if>
                </form>
                        <br/>
                 </div>
                           <c:if test="${not empty pageId}">
                 <div class="col-sm-5 well col-sm-offset-2">
                     <label ><b><u>Add/Edit Form Input ${fi.name}</u><b></label>
                                 <br/>
                <form action="${pageContext.request.contextPath}/admin/fi/build" method="POST" class="form-signin-heading" >
                    <input type="text" class="form-control" placeholder="Question" name="ftext" value="${fi.name}" required="true"/>
                        <br/>
                         <input type="text" class="form-control" placeholder="Type" name="ftype" value="${fi.type}" required="true"/>
                        <br/>
                         <input type="text" class="form-control" placeholder="Required" name="freq" value="${fi.mandatory}" required="true"/>
                        <br/>
                        <input style="display:none" type="text" class="form-control" name="pageId" value="${pageId}" required="true"/>
                        <br/>
                      <button type="submit" class="btn btn-sm" style="background-color:#515151;color:#ffffff" >Add</button>
                </form>
                 </div>
                        
               <div class="col-sm-5 well">
                     <label ><b><u>Add/Edit Tab ${tab.name}</u><b></label>
                                 <br/>
                <form action="${pageContext.request.contextPath}/admin/tab/build" method="POST" class="form-signin-heading" >
                         <input type="text" class="form-control" placeholder="Image" name="timage" value="${tab.imageUrl}" required="true" />
                        <br/>
                          <input type="text" class="form-control" placeholder="Url" name="turl" value="${tab.url}" />
                        <br/>
                         <textarea name="ttext" class="form-control" rows="5" placeholder="Text" required="true" >${tab.text}</textarea>
                        <br/>
                           <textarea name="tstext" class="form-control" rows="10" placeholder="Sub Text" >${tab.subText}</textarea>
                        <br/>
                        <input style="display:none" type="text" class="form-control" name="pageId" value="${pageId}" required="true"/>
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

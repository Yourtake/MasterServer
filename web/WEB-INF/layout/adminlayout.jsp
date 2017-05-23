<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


     
     <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Hi, ${admin.name}</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/admin/settings">Settings</a></li>
            <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
          </ul>
        </div>
      </div>
    </nav>

  <div class="container-fluid">
      <div class="row">
        
<div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar" >
              <li ><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin"><span style="font-size: 20px;" class="glyphicon glyphicon-bullhorn"></span>&nbsp;Home </a></li>
            <c:if test="${admin.power<=1&&admin.organization=='Your Take'}">  
                 <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/organizationBuilder"><span style="font-size: 20px;" class="glyphicon glyphicon-briefcase"></span>&nbsp;Organization Builder</a></li>
             </c:if>
               <c:if test="${admin.power<=1}">  
                    <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/branchBuilder"><span style="font-size: 20px;" class="glyphicon glyphicon-check"></span>&nbsp;Branch Builder</a></li>
                        <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/rules"><span style="font-size: 20px;" class="glyphicon glyphicon-adjust"></span>&nbsp;Rules</a></li> 
             </c:if>
              <c:if test="${admin.power<=2}">            
              <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/viewBuilder"><span style="font-size: 20px;" class="glyphicon glyphicon-bookmark"></span>&nbsp;View Builder</a></li> 
            <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/pageBuilder"><span style="font-size: 20px;" class="glyphicon glyphicon-align-center"></span>&nbsp;Page Builder</a></li> 
             <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/pageDetailsBuilder"><span style="font-size: 20px;" class="glyphicon glyphicon-arrow-left"></span>&nbsp;Page Details Builder</a></li> 
                   <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/iotTrackPanel"><span style="font-size: 20px;"class="glyphicon glyphicon-bell"></span>&nbsp;IoT Tracker</a></li>  
           
               </c:if>
             <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/liveView"><span style="font-size: 20px;"class="glyphicon glyphicon-upload"></span>&nbsp;Live View</a></li>   
            
            <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/hr"><span style="font-size: 20px;"class="glyphicon glyphicon-user"></span>&nbsp;People</a></li>
         
          </ul>

</div>








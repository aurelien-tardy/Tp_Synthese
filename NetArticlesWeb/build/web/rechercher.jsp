<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form form-horizontal" name="frmSearch" role="form" role="form" action="listeArticlesDomaine.na" method="post">
    <h1 align='center'><c:out value="${titre}"/></h1>                               
    <div class="form-group">
        <label class="col-md-3 control-label">Domaine : </label>
        <div class="col-md-3">
            <SELECT  class='form-control' name="cbDomaines" id="cbDomaines" onChange="chargerListe();">
                <option value="0" >Sélectionner un domaine</option>
                <c:forEach var="domaineE" items="${lDomainesR}">
                    <option value="${domaineE.idDomaine}" <c:if test="${domaineE.idDomaine == id_domaineR}"> SELECTED</c:if> >${domaineE.libdomaine}</option>
                </c:forEach>
            </SELECT>
        </div>
    </div>                  
</form>      
<c:if test="${id_domaineR != null}">
    <c:import url="listeArticles.jsp"/>      
</c:if>
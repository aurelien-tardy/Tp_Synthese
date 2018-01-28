<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-md-9 col-md-offset-2">
    <h1 align='center'><c:out value="${titre}"/></h1>
    <c:if test="${sessionScope.panier != null && !seesionScope.panier.isEmpty()}">
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <td>Id</td>
                    <td>Titre</td>
                    <td>Prix</td>                  
                    <td>Supprimer</td>                     
                </tr>
            </thead>
            <tbody>                
                <c:forEach var="articleE" items="${panier}">
                    <tr>
                        <td>${articleE.idArticle}</td>
                        <td>${articleE.titre}</td>
                        <td><fmt:formatNumber currencySymbol="&euro;" type="currency" value="${articleE.prix}"/></td>   
                        <td><a href="supprimerPanier.cde?id_article=${articleE.idArticle}">Supprimer</a></td>                         
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2" style="text-align: right">Montant total</td>                    
                    <td><fmt:formatNumber currencySymbol="&euro;" type="currency" value="${montantTotalR}"/></td>                       
                </tr> 

            </tbody>
        </table>
        <form role="form" method="post" action="confirmationPaiement.cde">
            <div>
                <div class="form-group">
                    <label class="col-md-2 control-label">Adresse email : </label>
                    <div class="col-md-6">
                        <input type="email" name="adresseEmail" class="col-md-10 form-control" required autofocus/>
                    </div>
                </div>
            </div>
            <br/>
            <br/>
            <div>
                <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-log-in"></span> Valider panier</button>    
            </div>
        </form>

    </c:if>
    <div>
        ${alreadyExist}       
    </div>
</div>


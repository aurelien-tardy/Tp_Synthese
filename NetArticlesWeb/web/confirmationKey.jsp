<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="well">
    <form class="form-signin form-horizontal" role="form" action="keyConfirmation.cde" method="post">  
        <h1 align='center'>Confirmation de Paiement - Code de confirmation</h1>
        <div class="form-group">
            <label class="col-md-3 control-label">Key</label>
            <div class="col-md-3">
                <input type="text" name="key" class="form-control" placeholder="Ex : T2HSD3H5" required autofocus>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-5 col-md-offset-3">
                <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-log-in"></span> Valider panier</button>    
                &nbsp;
            </div>
        </div> 
    </form>
</div>



<h1>TP de Synthèse - Informatique Répartie</h1>

<p><a href="http://localhost:8080/NetArticlesWeb/">Site utilisateur</a></p>
<p><a href="http://localhost:8080/AcquisitionWeb"/>Site acquisition</a></p>
<p>Pour tester les 2 sites, il faut importer les librairies jandex et hibernate dans les projets rest. (présents à la racine du projet)<p>

<h2>Apport Technologique:</h2>

<p>La technologie que nous avons choisie pour ce TP de synthèse permet de sécuriser les paiements d'articles. De nos jours de plus en plus de site nous propose une authentification à double facteur. Cependant la véritable crainte d'un utilisateur serait de se faire pirater son compte et que des achats non souhaités soit effectués réduisant ainsi le solde de son compte. Pour remédier à ce problème nous avons décidé de sécuriser cette étape avec l'envoi d'un mail de confirmation.</p>

<h3>Explications</h3>
<ol>
<li>L'utilisateur choisit ses articles et valide son panier en indiquant son adresse mail.</li>
<li>Un mail de confirmation est envoyé sur l'adresse mail spécifiée lors de la validation du panier.</li>
<li>Le client récupère le code de confirmation qui lui a été envoyé et l'insère dans le champ prévu à cet effet.</li>
<li>Si le code est correcte, le panier est validé sinon il a 3 essais avant d'annuler la validation du panier.</li>
</ol>

<p></p>
<b>Remarques:</b>
<br>
<ul>
<li>Nous aurions voulu ajouter un champ en base de données pour y insérer l'email du client pour rendre le système sécurisé. (nous avons évité pour les besoins du TP).</li>
<li>La génération du code de confirmation (coté serveur rest) est hashé (en MD5) pour plus de sécurité. Ainsi un "user-in-the-middle" ne pourrait pas utiliser le code car il est hashé. Pour comparer la clé entrée par l'utilisateur et la clé générée on compare le hash (MD5) de la clé entrée et celui de la clé générée.</li>
</ul>
<h3>Explication Technique:</h3>

<p>Lors du clic sur la validation du panier, le client appelle le WebService chargé de générer la clé et d'envoyer le mail à l'adresse indiquée. Puis il retourne le hash de cette clé au client. Une fois la clé rentrée le client compare les 2 hash des 2 clées, si elles sont égales alors on peut procéder au processus de validation du panier.<p>

<b>Remarque:</b>
<p>Pour l'envoi de mail nous utilisons une librairie Apache:  commons-email-1.5.jar</p>
<p>Il suffit de l'importer dans les librairies de BanqueRest.</p>

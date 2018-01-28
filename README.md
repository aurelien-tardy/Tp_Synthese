<h1>TP de Synthèse - Informatique Répartie</h1>

<b>URL TEST Net_ArticlesRest :</b> http://localhost:8080/Net_ArticlesRest/webresources/webservice/testws

<b>URL TEST BanqueRest :</b> http://localhost:8080/BanqueRest/webresources/webservice/testws

<h2>Apport Technologique:</h2>

<p>La technologie que nbous avons choisie pour ce TP de synthèse permet de sécuriser les paiements d'articles. De nos jours de plus en plus de site nous propose une authentification à double facteur. Cependant la véritable crainte d'un utilisateur serait de se faire pirater son compte et que des achats non souhaités soit effectués réduisant le solde du client. Pour remédier à ce soucis nous avons décidé de sécuriser cette étape par l'envoi d'un mail de confirmation.</p>

<h3>Explications</h3>
- L'utilisateur choisie ses articles et valide son panier en indiquant son adresse mail.
- Un mail de confirmation est envoyé sur l'adresse mail spécifiée lors de la validation du panier.
- Le client récupère le code de confirmation qui lui à été envoyé et l'insert dans le champ prévu à cet effet.
- Si le code est correcte le panier est validé sinon il a 3 essai avant d'annulé la validation du panier.

<b>Remarques:</b>
- Nous aurions voulu ajouter un champ en base de données pour y insérer l'email du client pour rendre le système sécruisé. (nous avons évité pour les besoins du TP).
- La génération du code de confirmation (coté serveur rest) est hashé (en MD5) pour plus de sécurité. Ainsi un "user-in-the-middle" ne pourrait pas utiliser le code car il est hashé. Pour comparer la clé entrée par l'utilisateur et la clé générée on compare le hash (MD5) de la clé entrée et celui de la clé générée.

<h3>Explication Technique:</h3>

<p>Lors du clique sur la validation du panier, le client appelle le WebService chargé de générer la clé et d'envoyer le mail à l'adresse indiqué. Puis il retourne le hash de cette clé au client. Une fois la clé rentrée le client compare les 2 hash des 2 clées, si elles sont égales alors on peut procéder au processus de validation du panier.<p>

<b>Remarque:</b>
<p>Pour l'envoie de mail nous utilisons une librairie apache:  commons-email-1.5.jar</p>
<p>Il suffit de l'importé dans les librairies de BanqueRest.</p>

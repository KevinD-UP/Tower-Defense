# TowerDefense

Bienvenue sur le répertoire du projet ``Tower Defense`` mené par le groupe composé de: Julia ARNOUX, Kévin DANG, Maxime DRESLER, Sarobidy RAPETERAMANANA

## Objectif du projet
Le but de ce projet est d'implémenter une version des jeux de type tower defense où l’objectif est de défendre une zone contre des vagues successives d’ennemis se déplaçant suivant un itinéraire ou non, en construisant et en améliorant progressivement des tours défensives.

# Comment démarrer le jeu

## Compilation

Voir le fichier ``BUILD.md``

## Exécution

Taper 
```
gradle run
```

# Etat d'avancement

## Etat actuel

![Screen du jeu](https://zupimages.net/up/20/20/iecl.png)

Le projet dans son état actuelle est fonctionnelle. Il est doté d'un système de sauvegarde vous permettant de continuer la progression à travers 11 niveaux, dont 1 en mode survival, c'est-à-dire que les vagues d'ennemies sont beaucoup plus nombreuses. 
Dans chaque niveau, vous disposez de trois types de tours différentes avec leurs caractéristiques. Une est moins précise que l'autre mais fera plus de dégât à l'impact.
Il faut résister aux vagues d'ennemis en les empêchant d'atteindre le bout du chemin. Pour cela, il faut saisir les tours avec la souris et les déposer sur le niveau afin de les construires tout en gardant un oeil à l'argent du jeu.
Si vous n'avez pas assez d'argent, vous ne pourrez pas construire la tour voulu. L'argent s'obtient en tuant des ennemis en plus d'un gain constant.
Une fois une tour construite, l'emplacement vide de la boutique est immédiatement rempli avec une tour aléatoire, il faut donc faire preuve de stratégie pour ne pas se retrouver submerger.

Il est également possible de démarrer une toute nouvelle partie à partir de zéro en appuyant sur le bouton **new game** présente dans l'écran de sélection des niveaux.

Note: Il est possible qu'à partir du niveau 5 le jeu devienne très difficile voire impossible dû à un mauvais équilibrage. Il est donc possible de modifier les valeurs des ennemies afin d'avancer dans le jeu et atteindre les niveaux suivant. 

## Fonctionnalités futures

* Meilleurs graphismes.
* Ajouter la possibilité d'améliorer les tours en jeu.
* En réponse à la fonctionnalité précédente, rendre les ennemies de plus en plus résistants au cours d'un niveau.
* Avoir certains types d'ennemis qui pourraient détruirent les tours posé.
* Différent type de tir pour les tours pouvant entrainer des dégâts de zone par exemple. 
* Un meilleur équilibrage.

## Architecture globale du projet

Un diagramme de classe est fourni ``Diagramme_TowerDefense.pdf`` vous permettant d'avoir une vue globale de l'architecture du projet.

## Rapport du projet

Voir ``Rapport.pdf``

## License

Ce logiciel est distribué sous la licence MIT.

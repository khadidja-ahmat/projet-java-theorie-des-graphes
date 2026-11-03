# Théorie des graphes en Java

Projet de **Théorie des graphes** (Licence 3 Informatique, Université de limoges), réalisé en binôme.

Trois algorithmes classiques, programmés en Java sans bibliothèque externe :

| Fichier | Algorithme |
|---|---|
| `src/AlgoDijkstra.java` | Plus court chemin entre deux sommets (**Dijkstra**) |
| `src/WelshPowell.java` | **Coloration** d'un graphe (Welsh-Powell) |
| `src/ComposantFortementConnexe.java` | **Composantes fortement connexes**, via la fermeture transitive de la matrice d'adjacence |

Chaque programme contient un graphe vu en TD et un graphe d'exemple de notre choix, écrits sous forme de matrice dans le code.

## Lancer

```bash
cd src
javac *.java
java AlgoDijkstra
java WelshPowell
java ComposantFortementConnexe
```

## Exemple de résultat (Dijkstra)

```
Plus court chemin de A à E : A --> F --> C --> D --> E
Distance totale : 7
```

## Rapport

Le rapport est dans [`docs/rapport-theorie-des-graphes.pdf`](docs/rapport-theorie-des-graphes.pdf).

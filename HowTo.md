# What is reported out of my datapoints? #
Data are uploaded from a file (see below), and transformed into a melody.
Beside the melody, a few informations are computed
  * values ranges (minimum and maximum for x and y
  * local extrema
  * a graphical plot

# Data #
## What kind of data file can I upload? ##

Basically, a data file contains 2 rows of numbers x/y, separated by spaces or tab characters, _e.g._

```
0	1
1	0.999200106660978
2	0.996801706302619
3	0.992808635853866
...
```

## Can I add header line to name the columns? ##

By default, the two columns are named _x_ and _y_, but if the first line is a text, then it will be parsed and considered as column names, _e.g_
```
time height
0	1
1	0.999200106660978
2	0.996801706302619
3	0.992808635853866
...
```

## Can I add comments? ##
Yep! all lines starting with a _#_ character will be considered as a comment. Comment line will be extracted and reported on the analysis page, _e.g._
```
#these data were collect on blabla and means nothing
time height
0	1
1	0.999200106660978
2	0.996801706302619
3	0.992808635853866
```

## I'd like to see it working but do not have any data available ##
No problem, check the example gallery
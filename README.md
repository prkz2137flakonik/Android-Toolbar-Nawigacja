
Pytania

1. Co sie stanie jesli app:showAsAction zamienisz na android:showAsAction?

Wszystkie pozycje menu ląduja pod trzema kropkami, nawet te z ifRoom lub always.
Atrybut android:showAsAction jest ignorowany przez biblioteke AppCompat.
Dziala tylko wariant z przedrostkiem app: (dlatego w menu musi byc xmlns:app).

2. Dlaczego ostatnia linia onOptionsItemSelected to return super... a nie return false?

super.onOptionsItemSelected przekazuje nieobsluzone pozycje do klasy nadrzednej.
To wlasnie klasa nadrzedna obsluguje strzalke powrotu (android.R.id.home).
return false tego nie robi - strzalka moglaby nie dzialac poprawnie.
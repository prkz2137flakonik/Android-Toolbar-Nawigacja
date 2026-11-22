Zapisy na kurs

Formularz zapisu na kurs – projekt z aplikacji mobilnych.

Zadanie 1



CheckBox uzywa interfejsu OnCheckedChangeListener ktory ma **jedna metode** – dlatego dziala lambda.

SeekBar uzywa interfejsu OnSeekBarChangeListener ktory ma **trzy metody** (onProgressChanged, onStartTrackingTouch, onStopTrackingTouch). Lambda dziala tylko przy jednej metodzie wiec trzeba napisac klase anonimowa.
 Zadanie 3 – czyszczenie formularza

Dodano przycisk „Wyczysc formularz”, który po potwierdzeniu w oknie AlertDialog zeruje wszystkie kontrolki ankiety.

Przed wyczyszczeniem formularza tworzony jest obiekt SurveyState, który przechowuje:
- pozycję wybraną w Spinnerze,
- wartości pięciu SeekBarów,
- treść komentarza i jego komunikat błędu,
- stan MaterialSwitch,
- stan CheckBox,
- stan przycisku wysyłania,
- treść podsumowania.

Po wyczyszczeniu wszystkie pola wracają do wartości początkowych. Usuwany jest również komunikat błędu z pola komentarza.

Snackbar udostępnia akcję „Cofnij”. Po jej wybraniu wywoływana jest metoda restoreSurveyState(), która przywraca wcześniej zapamiętane wartości kontrolek.

W formularzu ankiety satysfakcji nie występuje RadioGroup, dlatego nie było potrzeby używania clearCheck().

SeekBar.OnSeekBarChangeListener ma trzy metody: onProgressChanged(), onStartTrackingTouch() oraz onStopTrackingTouch().

Nie jest więc interfejsem funkcyjnym i nie można zapisać go za pomocą lambdy. Należy użyć klasy anonimowej i zaimplementować wszystkie wymagane metody.

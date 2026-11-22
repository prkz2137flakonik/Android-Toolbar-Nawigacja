
# Stos aktywności

## Przejście 1 – otwarcie formularza
[MainActivity]

## Przejście 2 – otwarcie podsumowania
[SummaryActivity]
[MainActivity]

## Wstecz – powrót do formularza
[MainActivity]

## Przejście 3 – otwarcie ekranu O aplikacji
[AboutActivity]
[MainActivity]

## Wstecz – powrót do formularza
[MainActivity]

## Wstecz – opuszczenie aplikacji
Stos aktywności zostaje opróżniony.

## Wnioski

Nowa aktywność jest umieszczana na szczycie stosu. Wywołanie finish() zamyka bieżącą aktywność i odsłania poprzednią. Przycisk Wstecz również powoduje powrót do poprzedniej aktywności.


---------------------------- PROCESS STARTED (7742) for package com.example.zapisy ----------------------------
2026-11-16 09:22:50.001  7742-7742  ActivityLifecycle       com.example.zapisy                   D  MainActivity - onCreate
2026-11-16 09:22:52.460  7742-7742  ActivityLifecycle       com.example.zapisy                   D  AboutActivity - onCreate
2026-11-16 09:22:53.008  7742-7742  ActivityLifecycle       com.example.zapisy                   D  MainActivity - onStop
2026-11-16 09:22:55.899  7742-7742  ActivityLifecycle       com.example.zapisy                   D  AboutActivity - onStop
2026-11-16 09:22:55.901  7742-7742  ActivityLifecycle       com.example.zapisy                   D  AboutActivity - onDestroy
2026-11-16 09:23:21.072  7742-7742  ActivityLifecycle       com.example.zapisy                   D  SummaryActivity - onCreate
2026-11-16 09:23:21.677  7742-7742  ActivityLifecycle       com.example.zapisy                   D  MainActivity - onStop
2026-11-16 09:23:26.122  7742-7742  ActivityLifecycle       com.example.zapisy                   D  SummaryActivity - onStop
2026-11-16 09:23:26.123  7742-7742  ActivityLifecycle       com.example.zapisy                   D  SummaryActivity - onDestroy
2026-11-16 09:23:41.135  7742-7742  ActivityLifecycle       com.example.zapisy                   D  AboutActivity - onCreate
2026-11-16 09:23:41.718  7742-7742  ActivityLifecycle       com.example.zapisy                   D  MainActivity - onStop
2026-11-16 09:23:46.367  7742-7742  ActivityLifecycle       com.example.zapisy                   D  AboutActivity - onStop
2026-11-16 09:23:46.368  7742-7742  ActivityLifecycle       com.example.zapisy                   D  AboutActivity - onDestroy

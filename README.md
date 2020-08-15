

# Android Technical Test

![Alt text](https://github.com/stephane-genicot/katas/raw/master/images/Kata_TicTacToe.png?raw=true)

## About this Kata

This short and simple Kata should be performed using **Test Driven Development** (TDD).

## Rules

The rules are described below :

- X always goes first.
- Players cannot play on a played position.
- Players alternate placing X’s and O’s on the board until either:
	- One player has three in a row, horizontally, vertically or diagonally
	- All nine squares are filled.
- If a player is able to draw three X’s or three O’s in a row, that player wins.
- If all nine squares are filled and neither player has three in a row, the game is a draw.


## Technologies Used

-Koin -DataBinding -MVVM -Rxjava -Expresso  -TesSubscriber 


## Architecture

This app uses a MVVM architecture with the following components

Model: Is effectively the output from the domain layer
View: The android activity and it's layout responsible for UI and user/system events
ViewModel: A simple datastore that is observed by the view to populate it's UI ( survive to the configuration changes and help us to deal with the memory leaks)

![alt text](https://miro.medium.com/max/1400/1*yhe1VFEZBjZI2bKJlZZvjg.png)



## Testing

In order to test the app I have used expresso to test some components of the ui (activity's visibility). To test rxjava observables I have used testObserbert to be
sure that some condition are respected (assertValues...)

## Posible updates

There some many things that we can improve like add ai using for instance minimax algorithm to play again the computer.
Other posible improvement would to add sounds, or save the historial's game using the room persitence library.












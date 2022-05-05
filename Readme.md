short instruction for my "Bumpers" Game:
    
SETUP:
- 
    To play the game at its full potential you only need to change two attributes in the car.java Class:
        - DEFAULT_CAR_WIDTH = 50 -> DEFAULT_CAR_WIDTH = 150
        - DEFAULT_CAR_HEIGHT = 25 -> DEFAULT_CAR_HEIGHT = 150
    This is due to the issue that in order to pass the Artemis tests you cannot change the proportions of your car which
    means that by using the original proportions given to us the characters look distorted (the game is still fully
    functional and playable even though I highly recommend changing as stated above those two attributes while playing)

CONTORLS
-
    SinglePlayer:
        - Mouseklick to choose your direction
        - MouseScrollWheel up/down to change your speed while moving
    Multiplayer:
        - Left Mouseklick for Player One
        - Right Mouseklick for Player Two


DAMAGE SYSTEM:
-
    Every character has three lifes. 
    When you loose all three of them, you've lost the game.
    Every character has a special ability:
        - Mario (the Player) can select his speed by scrolling up (accelerate) and down (decellerate) on the mousewheel.
        - Bowser's attack subtracts two lifes from the loser of the collision, but is therefore limited to a maximum 
          speed of 4
        - Donkey Kong is a normal character and can reach a maximum speed of 8. 

GAME MODES:
- 
    You can choose between a SinglePlayer and a local Multiplayer Mode by selecting the pressing the corrisponing buttons 
    on the topbar.
    The SinglePlayer Mode works just as the one given to us and shown in the lecture.
    The Multiplayer Mode supports two players on the same device and features a 1vs1 game.
    Initially I wanted to have the second player use keyboard controls, but due to technical issues all game controls are 
    limited to the mouse.
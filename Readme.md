short instruction for my "Bumpers" Game:
    
SETUP:
- 
    To play the game at its full potential you only need to set the following variable in the car.java Class to false:
        artemisMode = true; ---> artemisMode = false;
    This is due to the issue that in order to pass the Artemis tests you cannot change the proportions of your car which
    means that by using the original proportions given to us the characters look distorted. The game is still fully
    functional and playable even though I highly recommend setting it to false before playing as the game becomes way 
    more enjoyable then, thank you:)

CONTORLS
-
    Single Player:
        - Mouseclick to choose your direction
        - MouseScrollWheel up/down to change your speed while moving
    Multiplayer:
        - Left Mouseclick for Player One
        - Right Mouseclick for Player Two


DAMAGE SYSTEM:
-
    Every character has three lives. 
    When you lose all three of them, you've lost the game.
    Every character has a special ability:
        - Mario (the Player) can select his speed by scrolling up (accelerate) and down (decelerate) on the mousewheel.
        - Bowser's attack subtracts two lives from the loser of the collision, but is therefore limited to a maximum 
          speed of 3.
        - Donkey Kong does the same damage as Mario and can reach a maximum speed of 12. 
    //Note: Keep in mind that the hitbox of the characters is rather big due to the display of the remaining lives.
    //You can increase the difficulty of the game by adding more "cars" in the GameBoard.java class in line 24/25. 
GAME MODES:
- 
    You can choose between a Single Player and a local Multiplayer Mode by selecting the pressing the corresponding buttons 
    on the topbar.
    The Single Player Mode works just as the one given to us and shown in the lecture. You play as Mario and have to 
    fight/crash against Bowser and DonkeyKong.
    The Multiplayer Mode supports two players on the same device and features a 1vs1 game.
    Initially I wanted to have the second player use keyboard controls, but due to technical issues all game controls are 
    limited to the mouse.
COPYRIGHT
- 
    The used graphics, images and sound effects are from the following sources:
        - https://www.youtube.com/watch?v=-ytaJRn0RSU
        - https://www.youtube.com/watch?v=Pyqswng2xtA
        - https://www.youtube.com/watch?v=dQw4w9WgXcQ
        - https://nintendo-connect.de/heimkonsole/nintendo-switch/super-smash-bros-ultimate-stage-des-tages-schlachtfeld-mario-35722/#jp-carousel-35732
        - https://www.pikpng.com/pngvi/wJmioJ_transparent-donkey-kong-donkey-kong-png-transparent-clipart/
        - https://pngset.com/download-free-png-rszgr
        - https://www.pngwing.com/de/free-png-nezyf/download
        - https://imgbin.com/png/PKxXzUeL/minecraft-video-game-health-png
        - https://www.kindpng.com/imgv/ihxTJib_street-fighter-ko-png-street-fighter-ko-transparent/
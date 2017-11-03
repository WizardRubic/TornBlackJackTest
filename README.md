# TornBlackJackTest
I am testing whether torn.com online game blackjack is beatable using a simulation.

With the current program simulation it looks like it is beatable. 


# Potentially beatable ruleset

![Nash Beats the Casino](https://github.com/WizardRubic/TornBlackJackTest/blob/testmake/res/nashWins.png "Nash Beats the Casino")

It appears that many of the game's top players are profitable and one has made a claim about the game's flaws below: 

https://www.torn.com/forums.php#!p=threads&f=2&t=15985799&b=0&a=0&start=20&to=17328277
![Nash Claim 1](https://github.com/WizardRubic/TornBlackJackTest/blob/testmake/res/nashPost1.png "Nash Claim 1")
https://www.torn.com/forums.php#!p=threads&f=2&t=15985799&b=0&a=0&start=20&to=17328547
![Nash Claim 2](https://github.com/WizardRubic/TornBlackJackTest/blob/testmake/res/nashPost2.png "Nash Claim 2")


# Torn's Black jack rules:

Insurance:  
    Bet 5 dollars, if dealer has a natural win 10 dollars  
    tells us instantly if we won the insurance bet  
Split:  
    Seems you can double down on the second split and higher only  
    Also seems like we can see the dealer's cards on the second hand and up  
        tested when first hand busts  


#TODO
* Fix the markdown
* Host images elsewhere
* Test all the edge cases in the game's blackjack game
* Take in input to the black jack system, probably start small with just hand charts
* Consider using a hash map to store all the possible hand vals. 
* Auto generate the optimal strategy


# For Torn Players who may have discovered this repo:
If you don't understand what's going on here carry on ;) If you do understand I absolutely don't mind you seeing/using what's considering I put it up on GitHub. I'd appreciate it if this wasn't too widely distributed though! xD (I'm sure if it word gets out Chedburn will remove Blackjack from the casino haha!)
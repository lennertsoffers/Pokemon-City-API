# Analysis

The following topics where the result of my deeper analysis of the game, and working out most of the mechanics. The objects out of which the game exists are listed below. Their interaction with each other is described and their most important data as wel.

## Houses:
Houses are objects that have 2 major goals, providing citizens and collecting rent. Each type of house has different properties. The rent that is collected from the houses is dependent on the satisfaction of the city. They can be built, moved and demolished.

Houses generate rent over a certain period of time “RENT_PER_MINUTE”. When the stored rent is more than 50% of the maximum amount of rent the house can hold, the player can collect the rent. Rent will accumulate trough the time but will stop when the “MAX_RENT * city satisfaction” is reached.

Static data:
- Number of citizens
- Rent per minute
- Max rent
- Satisfaction modifier
- XP when finished build
- Price
- Unlocked at level
- Dimensions
- Image data

Attributes:
- Location
- Last collected

## Companies:

Companies are objects that provide money over time, without a max amount limit. Their profits can be collected, no matter what amount. The amount of profit is dependent on the amount of citizens and the city satisfaction.

Static data:
- Profit per minute
- Satisfaction modifier
- XP when finished build
- Price
- Unlocked at level
- Dimensions
- Image data

Attributes:
- Location
- Total profit

 
## Decorations:

Decorations are objects that provide satisfaction for your city.

Static data:
- Satisfaction modifier
- XP when finished build
- Price
- Unlocked at level
- Dimensions
- Image data

Attributes:
- Location

## Player:

A player is the object to identify users and track statistics.

Attributes:
- Nickname
- Email
- Password
- Level
- XP
- Money
- City*
- Statistics*

## City:

Collection of objects placed on the game map

Attributes:
- List of all objects
- List of all citizens
- Name
- Date created

 
## Statistics:

Statistics collect the data of a player

Attributes:
- Time played
- Total value
- Buildings build

## Citizens:

Citizens have their own specific stats. When a house is built, the number of citizens is created, and their stats are randomized. The player can assign citizens to a company. The profit from the company is modified regarding the amount of specialization this citizen has.

Citizens are not correspondent to houses. This means that on deletion of a house, the player can choose which citizens he wants to remove, so he/she can keep the good ones.

When the player assigns a citizen to a company, the specialization of that type will increase over time, but the other specialization values will decrease. This increase can happen until the max specialization amount of this citizen has been reached.

Attributes:
- Name
- Max specialization data:
    - Max cooking
    - Max social
    - Max service
    - Max selling
- Specialization data:
    - Cooking
    - Social
    - Service
    - Selling

## Roads:

Roads are a special type of decoration that connects houses and companies with your townhall. When buildings aren’t connected with your townhall, they won’t have any profits.

Static data:
- Satisfaction modifier
- XP when finished build
- Price
- Unlocked at level
- Dimensions
- Image data

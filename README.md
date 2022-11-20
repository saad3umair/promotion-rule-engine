
Promotion rule engine is simple rule engine applying rules defined for SKU Items in the cart.
This application is implementing 2 rules:
1. Single item promotion rule - applying rules defined for individual items like rule defined for item A having unit price 50. 
If cart contains 3 A's then promotion is applied for item A and price is reduced to 130.
2. Multi item promotion rule - applying rules defined for multi items like if cart contains 1 C having unit price 20 and 1 D having unit price 15 then promotion is applied 
and price is reduced to 30.

This application is scalable to promotion rules, just by adding new rules to rules engine.

Test Setup
Unit price for SKU IDs:
A 50
B 30
C 20
D 15

Active Promotions:
3 of A's for 130
2 of B's for 45
C & D for 30

Scenario A
1 * A 50
1 * B 30
1 * C 20

Total 100

Scenario B
5 * A 130 + 2*50
5 * B 45 + 45 + 30
1 * C 28

Total 370

Scenario C
3 * A 130
5 * B 45 + 45 + 1 * 30
1 * C -
1 * D 30

Total 280

Notes
• The promotion rules are mutually exclusive, that implies only one promotion (individual SKU or combined) is applicable to a particular SKU. Rest depends on the imagination of the programmer for which scenarios they want to consider, for example (case 1 => 2A = 30 and A=A40%) or (case 2 => either 2A = 30 or A=A40%)

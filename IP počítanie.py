from math import pow

"""
#Príkaz na mocninu je math.pow(číslo,mocnina)
"""
x =int(input("Zadajte počet PC:"))
mocnina = 1
while True:
    if  pow(2,mocnina) < x :
        mocnina=mocnina+1
    else :
        if pow(2,mocnina) >= x+2 :
            print('Dva na ',mocnina,'je viac ako ',x,)
            print("Ukladám" ,mocnina, "ako premennú ,,mocnina,,")
##            print(pow(2,y))
            break
        else :
            print('Mocnina nevychádza , je potrebné nechať 2 miesta pre brd. a adresu siete')
            mocnina=mocnina+1


        
#Oddelenie A,B a C

oktet1 =     str(input("Zadajte prvý oktet:"))
oktet2 =     str(input("Zadajte druhý oktet:"))
oktet3 =     str(input("Zadajte tretí oktet:"))
oktet4 =     str(input("Zadajte štvrtý oktet:"))
cislo_prv = str(input("Zadajte počet bitov NET:"))

print(oktet1,".",oktet2,".",oktet3,".",oktet4,"/",cislo_prv)

#PRVÝ OKTET
CA1=128
CA2=64
CA3=32
CA4=16

CB1=8
CB2=4
CB3=2
CB4=1

#DRUHÝ OKTET
MC1=128
MC2=64
MC3=32
MC4=16

MD1=8
MD2=4
MD3=2
MD4=1

#TRETÍ OKTET
YE1=128
YE2=64
YE3=32
YE4=16

YF1=8
YF2=4
YF3=2
YF4=1

#ŠTVRTÝ OKTET
KG1=128
KG2=64
KG3=32
KG4=16

KH1=8
KH2=4
KH3=2
KH4=1

import GregorianToJalali as GregorianToJalali
from datetime import datetime

# Getting current date
year = datetime.now().year
month = datetime.now().month 
day = datetime.now().day  

g = GregorianToJalali.GregorianToJalali()

# Tests
print(g.gregorianToJalaliStr(2021,11,1))
print(g.gregorianToJalaliStr(2022,1,7))
print(g.gregorianToJalaliStr(year,month,day))

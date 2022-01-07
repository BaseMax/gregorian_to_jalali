import GregorianToJalali as GregorianToJalali
from datetime import datetime

# getting current date
year = datetime.now().year
month = datetime.now().month 
day = datetime.now().day  


g = GregorianToJalali.GregorianToJalali()

print(g.gregorianToJalaliStr(2022,1,7))

print(g.gregorianToJalaliStr(year,month,day))


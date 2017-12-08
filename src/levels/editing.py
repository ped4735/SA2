import datetime
import os
import glob2


#pega os arquivos origiais, na pasta raw
os.chdir("raw")
#print (os.getcwd())
filenames=glob2.glob("*.tmx")
os.chdir("..")
#print (os.getcwd())

#escreve o nome, soh para garantir
#print(filenames)


#se a pasta ao existe, cria
if (os.path.exists("cleanMaps") == False):
    os.mkdir("cleanMaps")


#limpa as fases
for name in filenames:
    try:
        #pega da pasta raw
        fileToRead = open("raw/"+name,"r")
        #escreve na cleanMaps
        fileToWrite = open ("cleanMaps/"+name,"w")
        for line in fileToRead:
            #se nao comeca comeca com numero, eh info do tiled
            if line[0].isdigit():
                fileToWrite.write(line)

        fileToRead.close()
        fileToWrite.close()
    except:
        print ("ops. algo errado nao esta certo")


        
#escreve os nomes em um arquivo para importar pelo java
namesToPrint = ""
for name in filenames:
    #lembrar de tirar o .tmx para usar nosso metodo de importar java
    namesToPrint+="cleanMaps/"+name[:-4] + ","
#print(namesToPrint[:-1])


f = open("validMaps.txt","w")
f.write(namesToPrint[:-1] + "\n")

#ela invertida
namesToPrint = ""
filenames.reverse()
for name in filenames:
    #lembrar de tirar o .tmx para usar nosso metodo de importar java
    namesToPrint+="cleanMaps/"+name[:-4] + ","

print(namesToPrint[:-1])    
f.write(namesToPrint[:-1])


f.close()


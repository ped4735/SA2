import datetime
import os
import glob2

""" pega os arquvos do tiled e transforma em algo que o java le """
""" haha, agora tbm lê o arquivo e troca o codigo do heroi pelo da saída!!
    o dobro de fases em um click!
    
    ele le até a fase 99,
    lê outra vez trocando os códigos e criando o returnMap*.tmx
    cria uma lita com o as fases crescente, a fase 99 (sem return dela) e depois descrescente da fase return
    
    

"""
heroCode = "3"
exitCode = "9"

    
#pega os arquivos origiais, na pasta raw
os.chdir("raw")
filenames=glob2.glob("*.tmx")
os.chdir("..")



#se a pasta ao existe, cria
if (os.path.exists("cleanMaps") == False):
    os.mkdir("cleanMaps")

#a leitura, apagando as linhas inuteis
ida = []
volta = []
for name in filenames:
    try:
        
        #ida
        fileToRead = open("raw/"+name,"r")
        fileToWrite = open ("cleanMaps/"+name,"w")
        ida.append(name)
        for line in fileToRead:
            #se nao comeca comeca com numero, eh info do tiled
            if line[0].isdigit():
                fileToWrite.write(line)
        fileToRead.close()
        fileToWrite.close()


        #volta
        #trocando o hero pela exit e vice versa.
        #ELES NAO PODEM ESTAR NA MESMA LINHA! ou talvez possam, não sei. (#science)
        fileToRead = open("raw/"+name,"r")
        fileToWrite = open ("cleanMaps/return"+name,"w")
        volta.append("return"+name)

        for line in fileToRead:
            #se nao comeca comeca com numero, eh info do tiled
            if line[0].isdigit():

                 
                if heroCode in line:
                    
                    line = line.replace(heroCode,exitCode,1)
                    print("heroi trocado. file: return" + name)
                elif exitCode in line:
                    line = line.replace(exitCode,heroCode,1)
                    print("saida trocado. file: return" + name)

                    #print("saida trocado")
                    
                fileToWrite.write(line)

                
        fileToRead.close()
        fileToWrite.close()
        
    except:
        print ("ops. algo errado nao esta certo")


#concatena as listas. remove o ultimo da volta (que é a fase 99. não quero repetir ela)
volta.pop()
volta.reverse()
idaEvolta = ida[:] + volta[:]
#print (idaEvolta)






#escreve os nomes em um arquivo para importar pelo java
f = open("validMaps.txt","w")

namesToPrint = ""
for name in idaEvolta:
    #lembrar de tirar o .tmx para usar nosso metodo de importar java
    namesToPrint+="cleanMaps/"+name[:-4] + ","
print("CORRETO:")
#[:-1] para remover a ultima virgula...
print(namesToPrint[:-1])
f.write(namesToPrint[:-1] + "\n")



#ela invertida na linha de baixo
namesToPrint = ""
idaEvolta.reverse()
for name in idaEvolta:
    #lembrar de tirar o .tmx para usar nosso metodo de importar java
    namesToPrint+="cleanMaps/"+name[:-4] + ","

print("INVETRIDO:")
print(namesToPrint[:-1])
f.write(namesToPrint[:-1] + "\n")



f.close()


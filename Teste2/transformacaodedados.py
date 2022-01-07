import tabula
import zipfile

pdf_path = "https://www.gov.br/ans/pt-br/arquivos/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-tiss/padrao-tiss/padrao-tiss_componente-organizacional_202111.pdf"

lista_de_tabelas = tabula.read_pdf(pdf_path, pages="114-120")

zf = zipfile.ZipFile('Teste_Debora_Carvalho.zip','w')

# Ajustes Quadro 30
quadro30 = lista_de_tabelas[0]
quadro30.columns = quadro30.iloc[0]
quadro30 = quadro30[1:]
quadro30[['Código', 'Descrição da categoria']] = quadro30['Código Descrição da categoria'].str.split(" ",n=1, expand=True)
quadro30.loc[3]
quadro30 = quadro30.drop(columns='Código Descrição da categoria')
quadro30 = quadro30.set_index("Código")

# Ajustes Quadro 31 
quadro31 = lista_de_tabelas[1]
quadro31.columns = quadro31.iloc[0]
quadro31 = quadro31[1:]
quadro31 = quadro31.set_index('Código')

# Ajustes Quadro 32
quadro32 = lista_de_tabelas[7]
quadro32 = quadro32[2:]
quadro32[['Código', 'Descrição da categoria']] = quadro32['Tabela de Tipo de Solicitação'].str.split(" ",n=1, expand=True)
quadro32.loc[2]
quadro32 = quadro32.drop('Tabela de Tipo de Solicitação', axis=1)
quadro32 = quadro32.set_index('Código')
quadro32 = quadro32.dropna(how='all')



for i in range(len(lista_de_tabelas)):
   if (i == 0):
      quadro30.to_csv(f'Quadro30.csv')
      zf.write('Quadro30.csv')
   if (i == 1):
      quadro31.to_csv(f'Quadro31.csv')
      zf.write('Quadro31.csv')
   if (i > 1 & i < 7 ):
      lista_de_tabelas[i].to_csv(f'Quadro31_{i}.csv')
      zf.write(f'Quadro31_{i}.csv')
   if (i == 7):
      quadro32.to_csv(f'Quadro32.csv')
      zf.write('Quadro32.csv')


zf.close()
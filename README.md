# Takehome Exercise

Este exercício é para a vaga de Software Engineer da Brasil Paralelo, e tem foco
em Backend. O objetivo é que mostre facilidade em aprender uma nova linguagem ou
habilidade nela, utilização das ferramentas, criação de testes, e organização
pessoal. Dê o seu melhor, mas não esperamos que gaste mais de 3-5 horas nesse
projeto, após se habituar com as ferramentas utilizadas.

Após ler o Briefing abaixo, escreva o código e testes para implementar a
funcionalidade solicitada em Clojure, conforme repositório. Há um código inicial
que você poderá utilizar se quiser como base em `src/takehome/core.clj`, assim
como alguns testes iniciais em `test/takehome/core_test.clj`. Há também uma
listagem de produções, que poderá copiar para seus testes, em
`resources/content-database.edn`.

Você precisará ter instalado java e as ferramentas de linha de comando de
clojure, conforme https://clojure.org/guides/getting_started. Você poderá rodar
os testes com `clj -Atest`. Utilize Git para separar os commits. Compacte o
repositório num .zip, e entregue por e-mail para konrad@brasilparalelo.com.br.

# Briefing

Na Brasil Paralelo criamos diversos tipos de mídia, como podcasts, cursos,
entrevistas, e debates. No momento, o controle de acesso a este conteúdo é feito
estritamente de acordo com a assinatura do usuário. Estamos considerando
utilizar regras mais flexíveis de acesso para permitir que estes acessem mais
tipos de conteúdo, desde que produzidos durante o período de vigência de suas
assinaturas, para que haja maior incentivo de renová-las.

As regras que testaremos são as seguintes:
- Todos os usuários terão acesso a nossas **séries**
- Usuários de tipo **patriota** terão acesso a todos os podcasts, todos os
  debates, e também a todas as entrevistas lançadas durante a validade de sua
  assinatura (12 meses)
- Usuários de tipo **premium** terão acesso a tudo que os patriotas têm, mais
  acesso a todos os cursos
- Usuários de tipo **mecenas** terão acesso a tudo que os usuários premium têm,
  mais os "relatórios mecenas" lançados durante a validade de sua assinatura (12
  meses)


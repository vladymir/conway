## Clojure

- Caracteristicas Basicas
    * Lisp
      + Criada por John Mcarty
      + Inspirada no lambda calculus Church 1939
    * Notação prefixada;
      Chamada de função em geral: function(param1, param2, ..., paramN);
      (function param1 param2)
      Operadores tambem sao funçoes +,-,/,*
      (+ 10 20 30)   => 60
      (= true false) => false
      Numero arbitrário de parametros
      (* 1 2 3 4 5 6 ...)
      Expressoes aninhadas
      (+ 10 (* 3 2) (- 10 3))
      Ordem de avaliaçao da esquerda pra direita e de dentro pra fora
      (+ 10 (+ 5 (+ 3 (+ 3 4))))
    * Parentesis ;)
- Homoiconicity
  * Código é dado e dado é código
- Estruturas de dados imutáveis (persistentes)
  * Persistência (neste context) != Persistência (banco de dados)
  * Imutabilidade via compartilhamento estrutural

- Programação Funcional
  * Fluxo de dados
  * Composição de funções
    - (f o g) = f(g(x)) o retorno de g deve ser do mesmo tipo da entrada de f
    - (g o f) = g(f(x))              f                                      g
    - (f o g o h)(x) = f(g(h(x)))
  * Funções puras (sem efeitos colaterais)
  * Imutabilidade
    - no assignments
  * Funções de alta ordem
    - funções que recebem funções como parâmetro
  * Funções como elementos de primeira classe (firstclass citizens)
    - podem ser passadas como parametro e declaradas dentro de outras funcoes
  * Recursividade
  * Closure
    - Maneira de manter "estado"
    - Dualidade OO - Closure
      + Objeto -> Dados com comportamento
      + Closure -> Comportamento com dados
      

* Linguagens imperativas são baseadas em sequencia de comandos
* Linguagens funcionais são baseadas em chamadas de funções aninhadas

* Em linguagens imperativas, um mesmo nome (variável) pode estar associada a diversos valores, em linguagens funcionais um nome é associado a um unico valor.

* Linguagens imperativas tem uma ordem de avaliação fixada. Em linguagens funcionais não necessariamente.

* Em linguagens imperativas novos valores podem ser associados com o mesmo nome através de repetições de comandos enquanto que em linguagens funcionais novos nomes são associados com novos valores através de chamadas recursivas.

* Em linguagens funcionais, funções são valores.


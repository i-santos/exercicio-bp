(ns takehome.core-test
  (:require [clojure.test :refer [is testing deftest]]
            [java-time :as time]
            [takehome.core :as sub]))

(let [; série
      brasil-entre-armas-e-livros {:type :series
                                   :name "1964: O Brasil entre Armas e Livros"
                                   :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
      ; podcast
      democracia-em-debate {:type :podcast
                            :name "Democracia em Debate"
                            :released-at (time/local-date-time "2020-03-29T20:02:34.340")}
      ; debate
      qual-o-limite-do-respeito {:type :debate
                                 :name "Qual o limite do Respeito?"
                                 :released-at (time/local-date-time "2020-03-29T20:02:34.351")}
      ; entrevista
      congresso-rafael-nogueira {:type :interview
                                 :name "Congresso Brasil Paralelo - Rafael Nogueira"
                                 :released-at (time/local-date-time "2019-11-16T21:40:51.579")}
      ; curso
      o-que-é-capitalismo {:type :course
                           :name "O Que é Capitalismo?"
                           :released-at (time/local-date-time "2019-01-31T03:24:59.633")}
      ; relatório mecenas
      relatorio-mecenas {:type :patron
                         :name "Relatório Mecenas"
                         :released-at (time/local-date-time "2020-08-10T20:00:00.656")}]

  ;;; ROLE: usuario
  (deftest test-can-usuario-access?
    (testing
     "if role 'usuario' can access specific content"
      ; series
      (is (= true (sub/can-usuario-access? brasil-entre-armas-e-livros)))
      ; podcast
      (is (= false (sub/can-usuario-access? democracia-em-debate)))
      ; debate
      (is (= false (sub/can-usuario-access? qual-o-limite-do-respeito)))
      ; interview
      (is (= false (sub/can-usuario-access? congresso-rafael-nogueira)))
      ; course
      (is (= false (sub/can-usuario-access? o-que-é-capitalismo)))
      ; patron
      (is (= false (sub/can-usuario-access? relatorio-mecenas)))))

  ;;; ROLE: patriota
  (deftest test-can-patriota-access?
    (testing "if role 'patriota' can access specific content"
      ; series
      (is (= true (sub/can-patriota-access? brasil-entre-armas-e-livros)))
      ; podcast
      (is (= true (sub/can-patriota-access? democracia-em-debate)))
      ; debate
      (is (= true (sub/can-patriota-access? qual-o-limite-do-respeito)))
      ; interview
      (is (= true (sub/can-patriota-access? congresso-rafael-nogueira)))
      ; course
      (is (= false (sub/can-patriota-access? o-que-é-capitalismo)))
      ; patron
      (is (= false (sub/can-patriota-access? relatorio-mecenas)))))

  ;;; ROLE: premium
  (deftest test-can-premium-access?
    (testing "if role 'premium' can access specific content"
      ; series
      (is (= true (sub/can-premium-access? brasil-entre-armas-e-livros)))
      ; podcast
      (is (= true (sub/can-premium-access? democracia-em-debate)))
      ; debate
      (is (= true (sub/can-premium-access? qual-o-limite-do-respeito)))
      ; interview
      (is (= true (sub/can-premium-access? congresso-rafael-nogueira)))
      ; course
      (is (= true (sub/can-premium-access? o-que-é-capitalismo)))
      ; patron
      (is (= false (sub/can-premium-access? relatorio-mecenas)))))

  ;;; ROLE: mecenas
  (deftest test-can-mecenas-access?
    (testing "if role 'mecenas' can access specific content"
      ; series
      (is (= true (sub/can-mecenas-access? brasil-entre-armas-e-livros)))
      ; podcast
      (is (= true (sub/can-mecenas-access? democracia-em-debate)))
      ; debate
      (is (= true (sub/can-mecenas-access? qual-o-limite-do-respeito)))
      ; interview
      (is (= true (sub/can-mecenas-access? congresso-rafael-nogueira)))
      ; course
      (is (= true (sub/can-mecenas-access? o-que-é-capitalismo)))
      ; patron
      (is (= true (sub/can-mecenas-access? relatorio-mecenas))))))
(ns takehome.core-test
  (:require [clojure.test :refer [is testing deftest]]
            [java-time :as time]
            [takehome.core :as sub]))

(let [; usuários
      usuario-2018 {:type               :usuario
                    :subscription-start (time/local-date-time "2018-01-01T00:00:00.000")
                    :subscription-end   (time/local-date-time "2019-01-01T00:00:00.000")}
      usuario-2019 {:type               :usuario
                    :subscription-start (time/local-date-time "2019-01-01T00:00:00.000")
                    :subscription-end   (time/local-date-time "2020-01-01T00:00:00.000")}
      usuario-2020 {:type               :usuario
                    :subscription-start (time/local-date-time "2020-01-01T00:00:00.000")
                    :subscription-end   (time/local-date-time "2021-01-01T00:00:00.000")}
      patriota-2018 {:type               :patriota
                     :subscription-start (time/local-date-time "2018-01-01T00:00:00.000")
                     :subscription-end   (time/local-date-time "2019-01-01T00:00:00.000")}
      patriota-2019 {:type               :patriota
                     :subscription-start (time/local-date-time "2019-01-01T00:00:00.000")
                     :subscription-end   (time/local-date-time "2020-01-01T00:00:00.000")}
      patriota-2020 {:type               :patriota
                     :subscription-start (time/local-date-time "2020-01-01T00:00:00.000")
                     :subscription-end   (time/local-date-time "2021-01-01T00:00:00.000")}
      premium-2018 {:type               :premium
                    :subscription-start (time/local-date-time "2018-01-01T00:00:00.000")
                    :subscription-end   (time/local-date-time "2019-01-01T00:00:00.000")}
      premium-2019 {:type               :premium
                    :subscription-start (time/local-date-time "2019-01-01T00:00:00.000")
                    :subscription-end   (time/local-date-time "2020-01-01T00:00:00.000")}
      premium-2020 {:type               :premium
                    :subscription-start (time/local-date-time "2020-01-01T00:00:00.000")
                    :subscription-end   (time/local-date-time "2021-01-01T00:00:00.000")}
      mecenas-2018 {:type               :mecenas
                    :subscription-start (time/local-date-time "2018-01-01T00:00:00.000")
                    :subscription-end   (time/local-date-time "2019-01-01T00:00:00.000")}
      mecenas-2019 {:type               :mecenas
                    :subscription-start (time/local-date-time "2019-01-01T00:00:00.000")
                    :subscription-end   (time/local-date-time "2020-01-01T00:00:00.000")}
      mecenas-2020 {:type               :mecenas
                    :subscription-start (time/local-date-time "2020-01-01T00:00:00.000")
                    :subscription-end   (time/local-date-time "2021-01-01T00:00:00.000")}
      ; série
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
  (deftest test-can-usuario-role-access?
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
  (deftest test-can-patriota-role-access?
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
  (deftest test-can-premium-role-access?
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
  (deftest test-can-mecenas-role-access?
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
      (is (= true (sub/can-mecenas-access? relatorio-mecenas)))))

  (deftest test-can-usuario-instance-access?
    (testing "if an instance of 'usuario' can access specific content"
      ; series released at 2019-07-24
      (is (= false (sub/can-access? usuario-2018 brasil-entre-armas-e-livros)))
      (is (= true  (sub/can-access? usuario-2019 brasil-entre-armas-e-livros)))
      (is (= false (sub/can-access? usuario-2020 brasil-entre-armas-e-livros)))
      ; podcast released at 2020-03-29
      (is (= false (sub/can-access? usuario-2018 democracia-em-debate)))
      (is (= false (sub/can-access? usuario-2019 democracia-em-debate)))
      (is (= false (sub/can-access? usuario-2020 democracia-em-debate)))
      ; debate released at 2020-03-29
      (is (= false (sub/can-access? usuario-2018 qual-o-limite-do-respeito)))
      (is (= false (sub/can-access? usuario-2019 qual-o-limite-do-respeito)))
      (is (= false (sub/can-access? usuario-2020 qual-o-limite-do-respeito)))
      ; interview released at 2019-11-16
      (is (= false (sub/can-access? usuario-2018 congresso-rafael-nogueira)))
      (is (= false (sub/can-access? usuario-2019 congresso-rafael-nogueira)))
      (is (= false (sub/can-access? usuario-2020 congresso-rafael-nogueira)))
      ; course released at 2019-01-31
      (is (= false (sub/can-access? usuario-2018 o-que-é-capitalismo)))
      (is (= false (sub/can-access? usuario-2019 o-que-é-capitalismo)))
      (is (= false (sub/can-access? usuario-2020 o-que-é-capitalismo)))
      ; patron released at 2020-08-10
      (is (= false (sub/can-access? usuario-2018 relatorio-mecenas)))
      (is (= false (sub/can-access? usuario-2019 relatorio-mecenas)))
      (is (= false (sub/can-access? usuario-2020 relatorio-mecenas)))))

  (deftest test-can-patriota-instance-access?
    (testing "if an instance of 'patriota' can access specific content"
      ; series released at 2019-07-24
      (is (= false (sub/can-access? patriota-2018 brasil-entre-armas-e-livros)))
      (is (= true  (sub/can-access? patriota-2019 brasil-entre-armas-e-livros)))
      (is (= false (sub/can-access? patriota-2020 brasil-entre-armas-e-livros)))
      ; podcast released at 2020-03-29
      (is (= false (sub/can-access? patriota-2018 democracia-em-debate)))
      (is (= false (sub/can-access? patriota-2019 democracia-em-debate)))
      (is (= true (sub/can-access? patriota-2020 democracia-em-debate)))
      ; debate released at 2020-03-29
      (is (= false (sub/can-access? patriota-2018 qual-o-limite-do-respeito)))
      (is (= false (sub/can-access? patriota-2019 qual-o-limite-do-respeito)))
      (is (= true (sub/can-access? patriota-2020 qual-o-limite-do-respeito)))
      ; interview released at 2019-11-16
      (is (= false (sub/can-access? patriota-2018 congresso-rafael-nogueira)))
      (is (= true (sub/can-access? patriota-2019 congresso-rafael-nogueira)))
      (is (= false (sub/can-access? patriota-2020 congresso-rafael-nogueira)))
      ; course released at 2019-01-31
      (is (= false (sub/can-access? patriota-2018 o-que-é-capitalismo)))
      (is (= false (sub/can-access? patriota-2019 o-que-é-capitalismo)))
      (is (= false (sub/can-access? patriota-2020 o-que-é-capitalismo)))
      ; patron released at 2020-08-10
      (is (= false (sub/can-access? patriota-2018 relatorio-mecenas)))
      (is (= false (sub/can-access? patriota-2019 relatorio-mecenas)))
      (is (= false (sub/can-access? patriota-2020 relatorio-mecenas)))))

  (deftest test-can-premium-instance-access?
    (testing "if an instance of 'premium' can access specific content"
      ; series released at 2019-07-24
      (is (= false (sub/can-access? premium-2018 brasil-entre-armas-e-livros)))
      (is (= true  (sub/can-access? premium-2019 brasil-entre-armas-e-livros)))
      (is (= false (sub/can-access? premium-2020 brasil-entre-armas-e-livros)))
      ; podcast released at 2020-03-29
      (is (= false (sub/can-access? premium-2018 democracia-em-debate)))
      (is (= false (sub/can-access? premium-2019 democracia-em-debate)))
      (is (= true (sub/can-access? premium-2020 democracia-em-debate)))
      ; debate released at 2020-03-29
      (is (= false (sub/can-access? premium-2018 qual-o-limite-do-respeito)))
      (is (= false (sub/can-access? premium-2019 qual-o-limite-do-respeito)))
      (is (= true (sub/can-access? premium-2020 qual-o-limite-do-respeito)))
      ; interview released at 2019-11-16
      (is (= false (sub/can-access? premium-2018 congresso-rafael-nogueira)))
      (is (= true (sub/can-access? premium-2019 congresso-rafael-nogueira)))
      (is (= false (sub/can-access? premium-2020 congresso-rafael-nogueira)))
      ; course released at 2019-01-31
      (is (= false (sub/can-access? premium-2018 o-que-é-capitalismo)))
      (is (= true (sub/can-access? premium-2019 o-que-é-capitalismo)))
      (is (= false (sub/can-access? premium-2020 o-que-é-capitalismo)))
      ; patron released at 2020-08-10
      (is (= false (sub/can-access? premium-2018 relatorio-mecenas)))
      (is (= false (sub/can-access? premium-2019 relatorio-mecenas)))
      (is (= false (sub/can-access? premium-2020 relatorio-mecenas)))))

  (deftest test-can-mecenas-instance-access?
    (testing "if an instance of 'mecenas' can access specific content"
      ; series released at 2019-07-24
      (is (= false (sub/can-access? mecenas-2018 brasil-entre-armas-e-livros)))
      (is (= true  (sub/can-access? mecenas-2019 brasil-entre-armas-e-livros)))
      (is (= false (sub/can-access? mecenas-2020 brasil-entre-armas-e-livros)))
      ; podcast released at 2020-03-29
      (is (= false (sub/can-access? mecenas-2018 democracia-em-debate)))
      (is (= false (sub/can-access? mecenas-2019 democracia-em-debate)))
      (is (= true (sub/can-access? mecenas-2020 democracia-em-debate)))
      ; debate released at 2020-03-29
      (is (= false (sub/can-access? mecenas-2018 qual-o-limite-do-respeito)))
      (is (= false (sub/can-access? mecenas-2019 qual-o-limite-do-respeito)))
      (is (= true (sub/can-access? mecenas-2020 qual-o-limite-do-respeito)))
      ; interview released at 2019-11-16
      (is (= false (sub/can-access? mecenas-2018 congresso-rafael-nogueira)))
      (is (= true (sub/can-access? mecenas-2019 congresso-rafael-nogueira)))
      (is (= false (sub/can-access? mecenas-2020 congresso-rafael-nogueira)))
      ; course released at 2019-01-31
      (is (= false (sub/can-access? mecenas-2018 o-que-é-capitalismo)))
      (is (= true (sub/can-access? mecenas-2019 o-que-é-capitalismo)))
      (is (= false (sub/can-access? mecenas-2020 o-que-é-capitalismo)))
      ; patron released at 2020-08-10
      (is (= false (sub/can-access? mecenas-2018 relatorio-mecenas)))
      (is (= false (sub/can-access? mecenas-2019 relatorio-mecenas)))
      (is (= true (sub/can-access? mecenas-2020 relatorio-mecenas))))))
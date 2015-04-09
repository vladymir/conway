(ns conway.core (:use clojure.pprint)
  (:require [clojure.math.combinatorics :as combo]))
(use '[clojure.java.shell :only [sh]])
(require '[clojure.string :as str])


(defn randomize []
    (if (< (rand 100) 25) 1 0))

(defn make-grid [size]
    ;(into [] (for [c (range size)]
    ;    (into [] (for [r (range size)] (randomize))))))
    (partition size (take (* size size) (repeatedly #(randomize)))))

(defn vertical [line column func]
    (for [x (range (- line 1) (+ line 2))]
        [x (func column 1)]))

(defn horizontal [line column func]
    (for [x (range (- column 1) (+ column 2))]
        [(func line 1) x]))

(defn left [line column]
    (vertical line column -))

(defn right [line column]
    (vertical line column +))

(defn up [line column]
    (horizontal line column -))

(defn down [line column]
    (horizontal line column +))

(defn neighbours [line column]
  (distinct (reduce concat ((juxt left right up down) line column))))

(defn make-bounds [size]
    (fn [x] (cond (< x 0)    (- size 1)
                  (= x size)  0
                  :else       x)))

(defn normalize [neighbours size]
    (let [bounds (make-bounds size)]
        (map #(map bounds %) neighbours)))

(defn get-cell [[line column] matrix]
    (nth (nth matrix line) column))

(defn count-neighbours [line column world]
    (count (filter #(= 1 %)
        (map #(get-cell % world)
            (normalize (neighbours line column) (count world))))))

(defn make-neighbours-count [size world]
    (into [] (for [c (range size)]
        (into [] (for [r (range size)]
            (count-neighbours c r world))))))

(defn is-alive? [cell]
    (cond (= cell 1) true
          (= cell 0) false))

(defn conway-rules [[cell counter]]
    (cond (not (is-alive? cell))
                (if (= counter 3) 1 0)
          (is-alive? cell)
                (cond (or (< counter 2)
                          (> counter 3)) 0
                      (or (= counter 2)
                          (= counter 3)) 1)))

(defn generate-pairs [world counter-matrix]
    (partition (count world)
        (partition 2 (flatten (map interleave world counter-matrix)))))

(defn apply-conway-rules [cells]
    (map conway-rules cells))

(defn generate-offspring [world counters]
    (let [pairs (generate-pairs world counters)]
      (map apply-conway-rules pairs)))

(defn replace-numbers-to-chars [numbers]
    (map (fn [x] (cond (= x 0) " "
                       (= x 1) "*"
                       :else nil)) numbers))

(defn print_world [world]
    (let [pre-world (map replace-numbers-to-chars world)]
            (str/join "" (map println pre-world))))

(defn main [world counters]
    (let [size (count world)
          next-offspring (generate-offspring world counters)
          counters       (make-neighbours-count size next-offspring)]
          (do (pprint (:out (print_world world)))
              (print (:out (sh "clear")))
              (Thread/sleep 250))
          (recur next-offspring counters)))

(defn -main [arg]
    (let [size (Integer. arg)
          world (make-grid size)
          counters (make-neighbours-count (count world) world)]
            (main world counters)))

(defn myfoldL [operation p_seq]
    (loop [acc 0 s p_seq ]
        (if (empty? s)
            acc
            (recur (operation acc (first s)) (rest s)))))

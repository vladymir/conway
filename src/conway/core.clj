(ns conway.core)

(defn randomize []
    (if (< (rand 100) 25) 1 0))

(defn make-grid [size]
    (into [] (for [c (range size)]
        (into [] (for [r (range size)] (randomize))))))


(defn vertical [line column func]
    (for [x (range (- line 1) (+ line 2))] 
        [x (func column 1)]))

(defn horizontal [line column func]
    (for [x (range (- column 1) (+ column 2))] 
        [(func line 1) x]))

(defn left-of [line column] 
    (vertical line column -))

(defn right-of [line column]
    (vertical line column +))

(defn above-of [line column]
    (horizontal line column -))

(defn below-of [line column]
    (horizontal line column +))

(defn neighbours [line column]
    (into [] 
        (set (mapcat concat ((juxt left-of 
                                   right-of 
                                   above-of 
                                   below-of) line column)))))

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

(defn conway-rules [[counter cell]]
    (cond (= cell 0) (if   (= counter 3) 1)
          (= cell 1) (cond (< counter 2) 0
                           (or (= counter 2)
                               (= counter 3)) 1
                           (> counter 3) 0)))

(defn myfoldL [operation p_seq]
    (loop [acc 0 s p_seq ]
        (if (empty? s)
            acc
            (recur (operation acc (first s)) (rest s)))))
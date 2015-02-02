(ns conway.core)

(defn make-grid [line column]
    (for [c (range line)] (for [r (range column)] 0)))
	
(defn left-of [line column] 
    (for [x (range (- line 1) (+ line 2))] 
        [x (- column 1)]))

(defn right-of [line column height width]
    (cond
        (and (> line 0) (< line (dec height)) (> column 0) (< column (dec width)))
            (for [x (range (- line 1) (+ line 2))] 
                [x (+ column 1)])
        (= column (dec width)) 
            (cond (= line 0) [[(dec height) 0] [0 0] [1 0]]
                  (= line (dec height)) [[(dec (dec height)) 0] [(dec height) 0] [0 0]]
                  :else
                    (for [x (range (dec line) (inc (inc line)))] 
                        [x 0])
            ) 
        ))

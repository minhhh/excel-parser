(ns excel-parser.core
  (:require [clojure.string :as string])
  (:import [org.apache.poi.ss.usermodel WorkbookFactory]
           [org.apache.poi.poifs.filesystem NPOIFSFileSystem])
  (:gen-class))

(def number-format (java.text.NumberFormat/getInstance))
(doto number-format
  (.setGroupingUsed false)
  (.setMaximumFractionDigits 1000))

(defn get-cell-str [cell]
  (if cell
    (let [cell-type (. cell getCellType)]
      (case cell-type
        0 (. number-format format (. cell getNumericCellValue))
        1 (.. cell (getStringCellValue) (replace "\n" "\\n"))
        2 (try
            (. number-format format (. cell getNumericCellValue))
            (catch Exception e (.. cell (getStringCellValue) (replace "\n" "\\n"))))
        ""))
    ""))

(defn parse-xls [filename]
  (with-open [f (clojure.java.io/input-stream filename :encoding "UTF-8")]
    ;; (let [wb (WorkbookFactory/create (NPOIFSFileSystem. f))] ;; For workbook with a lot of weird unicode
    (let [wb (WorkbookFactory/create f)]
      (doseq [x (range (. wb getNumberOfSheets))
              :let [sheet (. wb getSheetAt x)]]
        (println (format "__SHEET:%s" (. sheet getSheetName) ))
        (when (> (. sheet getPhysicalNumberOfRows) 0)
          (doseq [x (range 0 (inc (. sheet getLastRowNum)))
                  :let [row (. sheet getRow x)]]
            (if row
              (println
                (string/join
                  "\t"
                  (map
                    (fn [x]
                      (get-cell-str (. row getCell x)))
                    (range 0 (. row getLastCellNum)))))
              (println))))))))

(defn -main
  [& args]
  (parse-xls (nth args 0)))
  ;; (parse-xls "test.xls"))

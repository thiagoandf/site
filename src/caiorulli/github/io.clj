(ns caiorulli.github.io
  (:require [stasis.core :as stasis]
            [hiccup.core :refer [html]]
            [caiorulli.github.cleaner :as cleaner]))

(def home-page
  (html [:html
         [:head
          [:meta {:charset "utf-8"}]
          [:meta {:name    "viewport"
                  :content "width=device-width, initial-scale=1"}]
          [:title "caiorulli.github.io"]]
         [:body
          [:h1 "Meu objetivo é a conquista!"]
          [:iframe {:width           "560"
                    :height          "315"
                    :src             "https://www.youtube.com/embed/80SSXCgdCuU"
                    :frameborder     "0"
                    :allow           "accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                    :allowfullscreen "true"}]]]))

(defn- get-pages []
  {"/index.html" home-page})

(def app
  (stasis/serve-pages get-pages))

(def export-dir "./caiorulli.github.io")

(defn- load-export-dir []
  (stasis/slurp-directory export-dir #"\.[^.]+$"))

(defn export
  "Export the entire site as flat files to the export-dir."
  []
  (let [old-files (load-export-dir)]
    (cleaner/empty-git-directory! export-dir)
    (stasis/export-pages (get-pages) export-dir)
    (println)
    (println "Export complete:")
    (stasis/report-differences old-files (load-export-dir))
    (println)))

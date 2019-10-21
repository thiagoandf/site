(ns caiorulli.github.io
  (:require [stasis.core :as stasis]
            [caiorulli.github.cleaner :as cleaner]))

(defn- get-pages []
  {"/index.html" "<h1>Hello world!</h1>"})

(def app
  (stasis/serve-pages get-pages))

(def target-dir "./caiorulli.github.io")

(defn export []
  (cleaner/empty-git-directory! target-dir)
  (stasis/export-pages (get-pages) target-dir))

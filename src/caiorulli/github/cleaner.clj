(ns caiorulli.github.cleaner
  (:require [clojure.java.io :as io]
            [stasis.core :as stasis])
  (:import (java.io File)))

(defn- normalize-path [^String path]
  (if (= stasis/fsep "/")
    path
    (.replaceAll path stasis/fsep-regex "/")))

(defn- get-path [#^File path]
  (normalize-path (.getPath path)))

(defn- delete-file-recursively [f]
  (if (.isDirectory f)
    (doseq [child (.listFiles f)]
      (delete-file-recursively child)))
  (io/delete-file f))

(defn empty-git-directory! [f]
  (let [f (io/file f)]
    (if (.isDirectory f)
      (doseq [child (.listFiles f)]
        (when-not (= (.getName child) ".git")
          (delete-file-recursively child)))
      (if (.exists f)
        (throw (Exception. (str (get-path f) " is not a directory.")))))))

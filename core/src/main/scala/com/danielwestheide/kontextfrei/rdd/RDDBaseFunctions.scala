package com.danielwestheide.kontextfrei.rdd

import com.danielwestheide.kontextfrei.DCollectionBaseFunctions
import org.apache.spark.rdd.RDD

import scala.collection.Map
import scala.reflect.ClassTag

private[kontextfrei] trait RDDBaseFunctions
    extends DCollectionBaseFunctions[RDD] {
  override final def cartesian[A: ClassTag, B: ClassTag](as: RDD[A])(
      bs: RDD[B]): RDD[(A, B)] =
    as.cartesian(bs)

  override final def collect[A: ClassTag, B: ClassTag](as: RDD[A])(
      pf: PartialFunction[A, B]): RDD[B] =
    as.collect(pf)
  override final def distinct[A: ClassTag](as: RDD[A]): RDD[A] = as.distinct()

  override final def map[A: ClassTag, B: ClassTag](as: RDD[A])(
      f: A => B): RDD[B] =
    as.map(f)

  override final def flatMap[A: ClassTag, B: ClassTag](as: RDD[A])(
      f: A => TraversableOnce[B]): RDD[B] =
    as.flatMap(f)

  override final def filter[A: ClassTag](as: RDD[A])(f: A => Boolean): RDD[A] =
    as.filter(f)

  override final def groupBy[A, B: ClassTag](as: RDD[A])(
      f: A => B): RDD[(B, Iterable[A])] =
    as.groupBy(f)

  override final def groupByWithNumPartitions[A, B: ClassTag](
      as: RDD[A])(f: A => B, numPartitios: Int): RDD[(B, Iterable[A])] =
    as.groupBy(f, numPartitios)

  override final def mapPartitions[A: ClassTag, B: ClassTag](as: RDD[A])(
      f: Iterator[A] => Iterator[B],
      preservesPartitioning: Boolean = false): RDD[B] =
    as.mapPartitions(f, preservesPartitioning)

  override final def keyBy[A: ClassTag, B](as: RDD[A])(
      f: A => B): RDD[(B, A)] =
    as.keyBy(f)

  override final def union[A: ClassTag](xs: RDD[A])(ys: RDD[A]): RDD[A] =
    xs.union(ys)

  override final def sortBy[A: ClassTag, B: ClassTag: Ordering](as: RDD[A])(
      f: (A) => B)(ascending: Boolean): RDD[A] =
    as.sortBy(f, ascending)

  override final def collectAsArray[A: ClassTag](as: RDD[A]): Array[A] =
    as.collect()

  override final def count[A](as: RDD[A]): Long = as.count()

  override final def countByValue[A: ClassTag](as: RDD[A])(
      implicit ord: Ordering[A]): Map[A, Long] =
    as.countByValue()

  override final def first[A: ClassTag](as: RDD[A]): A = as.first()

}

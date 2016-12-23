/*
 * Copyright 2016 Jürgen Dufner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.jdufner.microbenchmark;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.sqrt;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@Warmup(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS) // Default = 10 iterations
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS) // Default = 10 iterations
@State(Scope.Benchmark) // Steuert die Wiederverwendung der Instanzvariablen
@Fork(value = 3)
@Timeout(time =  10, timeUnit = TimeUnit.SECONDS)
public class MyBenchmark {

  private final int max = 10000;

  @Benchmark
  @Test
  public void primes() {
    List<Integer> primzahlen = new ArrayList<>();
    int divisionCounter = 0;
    for (int divisor = 2; divisor < max; divisor++) {
      boolean istPrimzahl = true;
      int wurzel = (int) sqrt(divisor);
      for (int dividend = 2; dividend <= wurzel; dividend++) {
        divisionCounter++;
        if (divisor % dividend == 0) {
          istPrimzahl = false;
          break;
        }
      }
      if (istPrimzahl) {
        primzahlen.add(divisor);
      }
    }
//    System.out.println(divisionCounter);
//    System.out.println(primzahlen);
  }

  @Benchmark
  @Test
  public void primes2() {
    List<Integer> primzahlen = new ArrayList<>();
    int divisionCounter = 0;
    for (int divisor = 2; divisor < max; divisor++) {
      boolean istPrimzahl = true;
      int wurzel = (int) sqrt(divisor);
      for (int primzahl : primzahlen) {
        if (primzahl <= wurzel) {
          divisionCounter++;
          if (divisor % primzahl == 0) {
            istPrimzahl = false;
            break;
          }
        } else {
          break;
        }
      }
      if (istPrimzahl) {
        primzahlen.add(divisor);
      }
    }
//    System.out.println(divisionCounter);
//    System.out.println(primzahlen);
  }

}

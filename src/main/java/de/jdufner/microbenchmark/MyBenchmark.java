/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package de.jdufner.microbenchmark;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.sqrt;

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

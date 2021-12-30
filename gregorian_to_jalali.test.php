<?php
/*
 * Language: PHP
 * Date: 2021/12/30
 * Name: gregorian_to_jalali.test.php
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */

require "gregorian_to_jalali.php";

assert(gregorian_to_jalali_str(2021, 12, 30) === "1400/10/09");

import subprocess
import string
from difflib import SequenceMatcher

message = "flag{"
cipher = "2 AD 85 90 41 47 51 D0 FF A3 71 9B C5 1 4 2F 60 0 5A 67 A0 43 EE A1 E6 BF AB 58 56 17 6E 8D AC EA F6 94 A6 68 67 15 22 1B 96 "

result = "2 AD 85 90 41 "

while not result == cipher:
	print(message)
	message = message + ' '
	for char in string.printable:
		message_temp = str(message)
		message_temp = list(message_temp)
		message_temp[-1] = char
		message_temp = ''.join(message_temp)
		p = subprocess.Popen(("./main_encryption"), stdout=subprocess.PIPE, stdin=subprocess.PIPE)
		out, _ = p.communicate(message_temp.encode())
		new_result = out.decode('utf-8').splitlines()[2]

		match1 = SequenceMatcher(None, cipher, result).find_longest_match(0, len(cipher), 0, len(result))
		
		match2 = SequenceMatcher(None, cipher, new_result).find_longest_match(0, len(cipher), 0, len(new_result))

		if match2.size > match1.size:
			message = list(message)
			message[-1] = char
			message = ''.join(message)
			result = str(new_result)

	
	

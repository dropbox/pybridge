import sys
import androidlog

class LogFile(object):
    def __init__(self, log_func):
        self.buffer = ''
        self.log_func = log_func
    def write(self, s):
        s = self.buffer + s
        lines = s.split("\n")
        for l in lines[:-1]:
            self.log_func(l)
        self.buffer = lines[-1]
    def flush(self):
        return

sys.stdout = LogFile(androidlog.log)
sys.stderr = LogFile(androidlog.logerr)
